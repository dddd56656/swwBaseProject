package com.sww.edu.front.course.service;

import com.sww.edu.front.course.model.response.CoursePurchasedRecordRespVo;
import com.sww.edu.front.course.model.response.PurchasedRecordForAppResult;

import java.util.List;

public interface CourseService {
    List<CoursePurchasedRecordRespVo> getAllCoursePurchasedRecord(Integer userId);
}
