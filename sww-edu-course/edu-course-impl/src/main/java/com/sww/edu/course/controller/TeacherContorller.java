package com.sww.edu.course.controller;

import com.sww.edu.course.api.dto.TeacherDTO;
import com.sww.edu.course.remote.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:   mkp
 * Date:     2020/7/6 19:20
 * Description:
 */

@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherContorller {

    @Autowired
    private TeacherService teacherService;
    /**
     * 通过课程Id获取老师信息
     * @param courseId
     * @return
     */
    @GetMapping(value = "/getTeacherByCourseId")
    TeacherDTO getTeacherByCourseId(Integer courseId){
        return teacherService.getByCourseId(courseId);
    }
}
