package com.sww.edu.front.course.service;

import com.sww.edu.front.course.model.response.CourseSectionListResult;

public interface SectionService {
    CourseSectionListResult getSectionInfoByCourseId(Integer userId, Integer courseId);
}
