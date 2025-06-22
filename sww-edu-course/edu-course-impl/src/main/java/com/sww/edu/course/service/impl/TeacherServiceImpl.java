package com.sww.edu.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sww.edu.course.entity.po.Teacher;
import com.sww.edu.course.mapper.TeacherMapper;
import com.sww.edu.course.service.ITeacherService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程节内容 服务实现类
 * </p>
 *
 * @author sww
 * @since 2020-06-11
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
