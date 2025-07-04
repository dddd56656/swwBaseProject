package com.sww.edu.pay.utils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.sww.edu.pay.config.WechatPayMchConfig;
import com.sww.edu.pay.entity.PayOrder;

/**
 * @author Piaoxu
 * @since 2019/4/22-16:08
 **/
public class WeixinUtils {

    public static Map<String, String> newWeixinBaseParams(WechatPayMchConfig wechatPayMchConfig, PayOrder order) {
        Map<String, String> params = new TreeMap<>();
        params.put("appid", wechatPayMchConfig.getAppId());
        params.put("mch_id", wechatPayMchConfig.getMchId());
        params.put("nonce_str", RandomStringUtils.randomAlphanumeric(16).toLowerCase());
        params.put("body", order.getProductName());
        params.put("notify_url", wechatPayMchConfig.getNotifyUrl());
        params.put("product_id", StringUtils.isBlank(order.getGoodsOrderNo()) ? String.valueOf(order.getProductId()) : order.getGoodsOrderNo());
        if (wechatPayMchConfig.getUseFakeMoney()) {
            params.put("total_fee", String.valueOf(1)); // 临时使用1分钱进行支付
        } else {
        	//TODO 记得测完改下
        	params.put("total_fee", String.valueOf(1)); // 临时使用1分钱进行支付
//            params.put("total_fee", String.valueOf(order.getAmount().multiply(new BigDecimal(100).multiply(new BigDecimal(order.getCount())))));
        }
        params.put("out_trade_no", order.getOrderNo());
        params.put("spbill_create_ip", order.getClientIp());
        return params;
    }

    public static Map<String, String> newWeixinPCParams(WechatPayMchConfig wechatPayMchConfig, PayOrder order) {
        Map<String, String> params = newWeixinBaseParams(wechatPayMchConfig, order);
        params.put("trade_type", "NATIVE");
        return params;
    }


    public static Map<String, String> newWeixinQueryParams(WechatPayMchConfig wechatPayMchConfig, PayOrder order) {
        Map<String, String> params = new TreeMap<>();
        params.put("appid", wechatPayMchConfig.getAppId());
        params.put("mch_id", wechatPayMchConfig.getMchId());
        params.put("nonce_str", RandomStringUtils.randomAlphanumeric(16).toLowerCase());
        params.put("out_trade_no", order.getOrderNo());
        return params;
    }

    public static Map<String, String> newWeixinRefundParams(WechatPayMchConfig wechatPayMchConfig, PayOrder order, double refundAmount, String reason) {
        Map<String, String> params = new TreeMap<>();
        params.put("appid", wechatPayMchConfig.getAppId());
        params.put("mch_id", wechatPayMchConfig.getMchId());
        params.put("nonce_str", RandomStringUtils.randomAlphanumeric(16).toLowerCase());
        params.put("out_trade_no", order.getOrderNo());
        params.put("out_refund_no", order.getOrderNo());
        params.put("total_fee", String.valueOf(order.getAmount().multiply(new BigDecimal(100))));
        params.put("refund_fee", String.valueOf((int) (refundAmount * 100)));
        params.put("refund_desc", reason);
        return params;
    }
}
