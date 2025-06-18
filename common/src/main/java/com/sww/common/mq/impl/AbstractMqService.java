package com.sww.common.mq.impl;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import com.alibaba.fastjson.JSON;
import com.sww.common.mq.RocketMqService;
import com.sww.common.mq.dto.BaseMqDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: su wei wei
 * @date:   2024年6月27日 下午2:04:16
 */
@Slf4j
public abstract class AbstractMqService implements RocketMqService {
	
	/**
	 * @author: su wei wei
	 * @date:   2024年6月27日 下午12:02:34   
	*/
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000L, multiplier = 2))
    abstract public void convertAndSend(String topic,BaseMqDTO<?> data);
    
    /**
	 * @author: su wei wei
	 * @date:   2024年6月27日 下午12:02:34   
	*/
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000L, multiplier = 2))
    abstract public void sendDelayed(String topic,BaseMqDTO<?> data,int delayLevel);
    
    /**
	 * @author: su wei wei
	 * @date:   2024年6月27日 下午12:02:34   
	*/
    @Recover
    public void recover(Exception ex, Object arg0,Object arg1) {
    	//TODO su wei wei 后续可以考虑持久化&报警
        log.error("AbstractMqService - recover - args0:{} arg1:{} ex",JSON.toJSONString(arg0),JSON.toJSONString(arg1),ex);
    }
}
