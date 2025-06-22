package com.sww.edu.boss.service;

import com.sww.edu.course.api.dto.LessonDTO;

/**
 * @author: ma wei long
 * @date:   2020年6月29日 下午10:43:06
*/
public interface ILessonService {
	
	/**
	 * @author: ma wei long
	 * @date:   2020年6月29日 下午10:46:15   
	*/
	boolean saveOrUpdate(LessonDTO lessonDTO);
}
