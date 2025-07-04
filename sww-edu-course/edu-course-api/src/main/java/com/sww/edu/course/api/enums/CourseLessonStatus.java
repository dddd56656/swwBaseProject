package com.sww.edu.course.api.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bobbao
 * @description
 * @date 2019-08-16 15:39
 */
public enum CourseLessonStatus {

    /**
     * 隐藏
     */
    HIDE(0, "隐藏"),
    UNRELEASE(1, "待更新"),
    RELEASE(2, "已发布");

    private static final Map<Integer, CourseLessonStatus> map;

    static {
        map = new HashMap();

        for (CourseLessonStatus courseLessonStatus : CourseLessonStatus.values()) {
            map.put(courseLessonStatus.getCode(), courseLessonStatus);
        }
    }

    private int code;
    private String showValue;

    CourseLessonStatus(int code, String showValue) {
        this.code = code;
        this.showValue = showValue;
    }

    public static CourseLessonStatus valueOf(Integer value) {
        return value == null ? null : map.get(value);
    }

    public int getCode() {
        return code;
    }

    public String getShowValue() {
        return showValue;
    }
}
