package com.sww.edu.pay.api.dto;

import java.io.Serializable;
import java.util.List;

import com.sww.edu.pay.api.enums.Source;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付请求对象   
 * ma wei long
 * 2020年6月17日 下午3:30:52
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayReqDTO  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4832273272836171358L;
	private String goodsOrderNo;//商品订单编号
    private String channel;//渠道(weChat-微信支付，AliPay-支付宝支付)
    private String returnUrl;//h5支付成功回调地址
    private Integer wxType;//如果是从微信浏览器过来的话，则使用该参数来获取真正的openId参数
    private Integer userid;//用户ID
    private Source source;// 支付来源
    private String clientIp;// 客户端IP地址
    private String openId;// JSAPI微信公众号支付用户的唯一标识
}