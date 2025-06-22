package com.sww.edu.message.mq.listener;

import java.util.List;
import java.util.UUID;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.sww.edu.common.constant.MQConstant;
import com.sww.edu.common.mq.RocketMqService;
import com.sww.edu.common.mq.dto.BaseMqDTO;
import com.sww.edu.common.mq.listener.AbstractMqListener;
import com.sww.edu.common.util.ValidateUtils;
import com.sww.edu.message.api.dto.LessonStatusReleaseDTO;
import com.sww.edu.message.service.IMessageService;

import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RocketMQMessageListener(topic = MQConstant.Topic.LESSON_STATUS_RELEASE, consumerGroup = "${rocketmq.producer.group}" + "_" + MQConstant.Topic.LESSON_STATUS_RELEASE)
public class LessonStatusReleaseListener extends AbstractMqListener<BaseMqDTO<LessonStatusReleaseDTO>> implements RocketMQListener<BaseMqDTO<LessonStatusReleaseDTO>>{
	

	
    @Override
    public void onMessage(BaseMqDTO<LessonStatusReleaseDTO> data) {

    }
}