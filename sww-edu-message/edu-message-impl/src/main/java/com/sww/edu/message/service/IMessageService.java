package com.sww.edu.message.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sww.edu.common.page.DataGrid;
import com.sww.edu.common.result.ResponseDTO;
import com.sww.edu.message.api.dto.MessageDTO;
import com.sww.edu.message.api.dto.MessageQueryDTO;
import com.sww.edu.message.entity.Message;
/**
 * @author: ma wei long
 * @date:   2020年6月28日 下午1:44:17
 */
public interface IMessageService extends IService<Message>{
	
	/**
	 * @author: ma wei long
	 * @date:   2020年6月28日 下午1:50:55   
	*/
	DataGrid<MessageDTO> getMessageByUserId(MessageQueryDTO messageQueryDTO);
	
	/**
	 * @author: ma wei long
	 * @date:   2020年6月29日 上午11:14:49   
	*/
	Boolean updateReadStatus(Integer userId);
	
	/**
	 * @author: ma wei long
	 * @date:   2020年6月30日 上午10:28:06   
	*/
	List<Integer> saveMessage(Integer lessonId);
	
	/**
	 * @author: ma wei long
	 * @date:   2020年6月30日 下午4:57:36   
	*/
	void sendMessage(com.sww.edu.message.api.dto.Message message);
	
	/**
	 * @author: ma wei long
	 * @date:   2020年7月13日 下午8:02:32   
	*/
	Boolean getUnReadMessageFlag(Integer userId);
}