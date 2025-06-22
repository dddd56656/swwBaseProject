package com.sww.edu.message.service;

import com.sww.edu.message.api.dto.Message;

/**
 * @author: ma wei long
 * @date:   2020年6月29日 下午3:39:47
 */
public interface IPushService{
	
	/**
     * 向页面推送消息
    */
    void push(Message message);
}