package com.sww.edu.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sww.edu.course.entity.po.Course;
import com.sww.edu.course.mapper.CourseMapper;
import com.sww.edu.course.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sww
 * @since 2020-06-17
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public IPage<Course> selectPage(Page page, Wrapper wrapper) {
        Page selectPage = courseMapper.selectPage(page, null);
        return selectPage;
    }


    @Override
    @Transactional
    public void courseAutoOnline() {
        UpdateWrapper<Course> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", 1).set("update_time", LocalDateTime.now());
        updateWrapper.eq("status",0).isNotNull("auto_online_time")
                .le("auto_online_time", LocalDateTime.now());
        this.update(updateWrapper);
    }
}
