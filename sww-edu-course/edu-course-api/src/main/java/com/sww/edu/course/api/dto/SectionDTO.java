package com.sww.edu.course.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author sww
 * @since 2020-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SectionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 章节名
     */
    private String sectionName;

    /**
     * 章节描述
     */
    private String description;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否可见
     */
    private Boolean isVisible;

    /**
     * 是否删除
     */
    private Boolean isDe;

    /**
     * 排序字段
     */
    private Integer orderNum;

    /**
     * 最后操作者ID
     */
    private Integer lastOperatorId;

    /**
     * 状态，0:隐藏；1：待更新；2：已发布
     */
    private Integer status;

    /**
     * 模块关联的课程
     */
    private List<LessonDTO> lessonDTOS;


}
