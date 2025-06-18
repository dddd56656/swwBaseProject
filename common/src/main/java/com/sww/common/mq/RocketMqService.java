package com.sww.common.mq;

import com.sww.common.mq.dto.BaseMqDTO;

/**
 * @author: su wei wei
 * @date:   2024年6月27日 下午12:02:28
*/
public interface RocketMqService {

	/**
	 * @author: su wei wei
	 * @date:   2024年6月27日 下午12:02:34   
	*/
	void convertAndSend(String topic,BaseMqDTO<?> data);
	
	/**
	 * @author: su wei wei
	 * topic 可以参考MQConstant
	 * delayLevel 0 不延时   可以参考 MQConstant.DelayLevel的值
	 * @date:   2024年6月27日 下午12:40:51   
	*/
	void sendDelayed(String topic,BaseMqDTO<?> data,int delayLevel);
}
