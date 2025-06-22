package com.sww.edu.message.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sww.edu.common.result.ResponseDTO;
import com.sww.edu.message.api.dto.Message;
import com.sww.edu.message.server.PushServer;

/**
 * @author: ma wei long
 * @date:   2020年6月28日 下午1:46:09
 */
@Controller
public class IndexController {
	
	
    @GetMapping("/index")
    public String getCourseOrderByOrderNo() {
        return "index";
    }
    
    @GetMapping("/sendMessage")
    @ResponseBody
    public ResponseDTO<?> sendMessage() {
    	Message message = new Message();
    	message.setContent("test");
    	message.setUserId(100029966);
    	PushServer.pushServer.push(message);
        return ResponseDTO.success();
    }
}
