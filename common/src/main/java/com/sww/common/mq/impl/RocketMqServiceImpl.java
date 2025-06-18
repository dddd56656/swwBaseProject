package com.sww.common.mq.impl;

import com.sww.common.mq.dto.BaseMqDTO;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sww.common.util.ValidateUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: su wei wei
 * @date:   2024年6月27日 下午12:02:58
*/
@Slf4j
@Service
public class RocketMqServiceImpl extends AbstractMqService{

	@Lazy
	@Autowired
    private RocketMQTemplate rocketMQTemplate;
	
	/**
	 * @author: su wei wei
	 * @date:   2024年6月27日 下午12:03:58
	*/
	@Override
	public void convertAndSend(String topic, BaseMqDTO<?> data) {
		ValidateUtils.notNullParam(topic);
		ValidateUtils.notNullParam(data);
		ValidateUtils.notNullParam(data.getData());
		rocketMQTemplate.asyncSend(topic, data, new SendCallback() {
            public void onSuccess(SendResult res) {
            	log.info("convertAndSend - onSuccess - topic：{} data:{} sendResult:{}",topic,JSON.toJSONString(data),JSON.toJSONString(res));
            }
            public void onException(Throwable e) {
            	log.error("convertAndSend - onException - topic：{} data:{} e:",topic,JSON.toJSONString(data),e);
            }
        });
	}

	/**
	 * @author: su wei wei
	 * delayLevel 0 不延时   可以参考 MQConstant.DelayLevel的值
	 * @date:   2024年6月27日 下午12:40:51   
	*/
	@Override
	public void sendDelayed(String topic, BaseMqDTO<?> data, int delayLevel) {
		ValidateUtils.notNullParam(topic);
		ValidateUtils.notNullParam(data);
		ValidateUtils.notNullParam(data.getData());
		ValidateUtils.isTrue(delayLevel >= 0, "延迟级别参数必须大于等于0");
		rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload(data).build(), new SendCallback() {
            public void onSuccess(SendResult res) {
            	log.info("sendDelayed - onSuccess - topic：{} data:{} sendResult:{}",topic,JSON.toJSONString(data),JSON.toJSONString(res));
            }
            public void onException(Throwable e) {
            	log.error("sendDelayed - onException - topic：{} data:{} e:",topic,JSON.toJSONString(data),e);
            }
        },5000,delayLevel);
	}
}
