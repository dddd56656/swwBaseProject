//package com.sww.edu.boss.rest;
//
//import com.alibaba.fastjson.JSON;
//import com.sww.edu.boss.entity.bo.UpLoadResult;
//import com.sww.edu.boss.entity.form.CourseForm;
//import com.sww.edu.boss.entity.vo.CourseVo;
//import com.sww.edu.boss.service.impl.CourseService;
//import com.sww.edu.boss.service.impl.OssService;
//import com.sww.edu.common.entity.vo.Result;
//import com.sww.edu.common.result.ResultCode;
//import com.sww.edu.course.api.CourseRemoteService;
//import com.sww.edu.course.api.dto.CourseDTO;
//import com.sww.edu.course.api.dto.PageResultDTO;
//import com.sww.edu.course.api.param.CourseQueryParam;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * Author:   lg
// * Date:
// * Description: 课程
// */
//@Api(tags = "课程", produces = "application/json")
//@Slf4j
//@Controller
//@RequestMapping("/course/")
//public class CourseController {
//
//    @Autowired
//    private CourseRemoteService courseRemoteService;
//    @Autowired
//    private OssService ossService;
//    @Autowired
//    private CourseService courseService;
//    @ApiOperation(value = "保存或者更新课程信息")
//    @PostMapping("saveOrUpdateCourse")
//    @ResponseBody
//    public Result<CourseDTO> saveOrUpdateCourse(@RequestBody CourseForm courseForm){
//
//    }
//    @ApiOperation(value = "通过课程Id获取课程信息")
//    @GetMapping("getCourseById")
//    @ResponseBody
//    public Result<CourseVo> getCourseById(@RequestParam("courseId") Integer courseId)  {
//
//    }
//    /**
//     *
//     */
//    @ApiOperation(value = "课程上下架")
//    @ApiImplicitParams ({
//            @ApiImplicitParam(name = "courseId", value = "课程ID"),
//            @ApiImplicitParam(name = "status", value = "课程状态，0-草稿，1-上架")
//    })
//    @GetMapping("changeState")
//    @ResponseBody
//    public Result changeState(@RequestParam("courseId") Integer courseId,
//                                @RequestParam("status") Integer status)  {
//
//    }
//
//    @ApiOperation(value = "分页查询课程信息")
//    @PostMapping("getQueryCourses")
//    @ResponseBody
//    public Result getQueryCourses(@RequestBody CourseQueryParam courseQueryParam)  {
//
//    }
//
//
//
//    @ApiOperation(value = "上传图片")
//    @PostMapping("/upload")
//    @ResponseBody
//    public Result upload(@RequestParam("file") MultipartFile multipartFile) {
//        UpLoadResult upLoadResult = ossService.upload(multipartFile);
//        return Result.success(upLoadResult);
//    }
//
//}
