package com.sww.edu.pay.trade.impl;

import java.io.StringWriter;
import java.text.ParseException;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sww.edu.common.date.DateUtil;
import com.sww.edu.common.exception.AlertException;
import com.sww.edu.common.util.ValidateUtils;
import com.sww.edu.pay.api.enums.Source;
import com.sww.edu.pay.api.enums.Status;
import com.sww.edu.pay.config.WechatPayMchConfig;
import com.sww.edu.pay.entity.PayOrder;
import com.sww.edu.pay.model.OutTrade;
import com.sww.edu.pay.service.IPayOrderService;
import com.sww.edu.pay.trade.AbstractThirdPayServer;
import com.sww.edu.pay.trade.request.wechatPay.WechatPayRequest;
import com.sww.edu.pay.trade.response.wechatPayResponse.WechatPayResponse;
import com.sww.edu.pay.utils.HttpUtils;
import com.sww.edu.pay.utils.OrderUtils;
import com.sww.edu.pay.utils.SignUtils;
import com.sww.edu.pay.utils.WeixinUtils;
import com.sww.edu.pay.utils.XmlUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:(微信支付服务)   
 * @author: ma wei long
 * @date:   2020年6月19日 下午1:39:06
*/
@Slf4j
@Service("weChat" + AbstractThirdPayServer.PAY_SERVER)
public class WechatPayServer extends AbstractThirdPayServer<WechatPayRequest,WechatPayResponse>{
	
	@Autowired
	private WechatPayMchConfig wechatPayMchConfig;
    @Autowired
    private IPayOrderService orderService;
    
	@Override
	public WechatPayResponse submitPay(WechatPayRequest request) {
		WechatPayResponse res = new WechatPayResponse();
		PayOrder order = request.getOrder();
        String url = order.getExtraElement(OrderUtils.PAY_URL);
        if (StringUtils.isNotEmpty(url)) {
        	res.setUrl(url);
            return res;
        }
        ValidateUtils.isTrue(request.getSource() == Source.PC, "目前值支持PC支付");
        Map<String, String> params = WeixinUtils.newWeixinPCParams(wechatPayMchConfig, order);
    	log.info("Wechat - submitPay request params:{}",JSON.toJSONString(params));
        Map<String, String> resp = doRequest(wechatPayMchConfig.getPayHost(), params, wechatPayMchConfig);
    	log.info("Wechat - submitPay response resp:{}",JSON.toJSONString(resp));
        url = resp.get("code_url");
        res.setUrl(url);
        if (url != null) {
            order.addExtra(OrderUtils.PAY_URL, url);
            PayOrder updateOrder = new PayOrder();
            updateOrder.setId(order.getId());
            updateOrder.setExtra(order.getExtra());
            orderService.updateById(updateOrder);
        }
        return res;
	}
	
	private Map<String, String> doRequest(String host, Map<String, String> params,WechatPayMchConfig wechatPayMchConfig){
        try {
        	SignUtils.addMd5Sign(params, wechatPayMchConfig.getPrivateKey());
            HttpEntity httpEntity = HttpUtils.postXmlWithoutSSlVerify(host, params);
            if (httpEntity == null) {
                log.error("发起请求后返回内容为空,访问地址:{}",host);
                return Collections.emptyMap();
            }
            StringWriter stringWriter = new StringWriter();
            IOUtils.copy(httpEntity.getContent(), stringWriter);
            String resp = stringWriter.toString();

            Map<String, String> respMap = XmlUtils.xmlToMap(resp);

            if (SignUtils.verfifyMd5Sign(respMap, wechatPayMchConfig.getPrivateKey())) {
                return respMap;
            }
            log.error("验证md5签证不正确:respMap{}",JSON.toJSONString(respMap));
            return Collections.emptyMap();
		} catch (Exception e) {
			log.error("微信支付请求失败 doRequest - params：{} - error",JSON.toJSONString(params),e);
			throw new AlertException("微信支付请求失败 - params： " + JSON.toJSONString(params)) ;
		}
    }
	
	@Override
	public WechatPayResponse callBack(WechatPayRequest request) {
		Map<String, String> paramMap = XmlUtils.xmlToMap(request.getParamStr());
		OutTrade trade = OutTrade.builder().status(Status.INVALID).build();
		WechatPayResponse response = new WechatPayResponse();
        if (!SignUtils.verfifyMd5Sign(paramMap, wechatPayMchConfig.getPrivateKey())) {
        	response.setTrade(trade);
            return response;
        }
        trade.setBuyId(paramMap.get(OrderUtils.OPENID));
        trade.setOutTradeNo(paramMap.get("transaction_id"));
        trade.setOrderNo(paramMap.get("out_trade_no"));
        trade.setStatus("SUCCESS".equals(paramMap.get("result_code")) ? Status.PAY_SUCCESS : Status.NOT_PAY);
        try {
			trade.setPayTime(DateUtil.string2Date(paramMap.get("time_end"), "yyyyMMddHHmmss"));
		} catch (ParseException e) {
			log.error("callBack - request：{} error",JSON.toJSONString(request),e);
		}
        response.setTrade(trade);
        response.setResStr("SUCCESS".equals(paramMap.get("result_code")) ? "<xml>\n" +
        	      "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
        	      "  <return_msg><![CDATA[OK]]></return_msg>\n" +
        	      "</xml>`" : null);
        return response;
	}
}
