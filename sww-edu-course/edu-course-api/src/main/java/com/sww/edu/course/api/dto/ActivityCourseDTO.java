package com.sww.edu.course.api.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: ma wei long
 * @date: 2020年7月6日 下午9:15:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("活动课程对象")
public class ActivityCourseDTO implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 667033264489150698L;

	@ApiModelProperty("活动课程id")
	private Integer id;

	@ApiModelProperty("课程id")
	private Integer courseId;

	@ApiModelProperty("活动开始时间")
	private Date beginTime;
	
	@ApiModelProperty("活动结束时间")
	private Date endTime;
	
	@ApiModelProperty("活动价格")
	private Double amount;
	
	@ApiModelProperty("库存值")
	private Integer stock;
	/**
	 * 状态 0未上架 10已上架
	 */
	@ApiModelProperty(hidden = true)
	private Integer status;
	/**
	 * 逻辑删除 0未删除 1删除
	 */
	@ApiModelProperty(hidden = true)
	private Integer isDel;
	/**
	 * 备注
	 */
	@ApiModelProperty(hidden = true)
	private String remark;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(hidden = true)
	private Date createTime;
	/**
	 * 创建人
	 */
	@ApiModelProperty(hidden = true)
	private String createUser;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(hidden = true)
	private Date updateTime;
	/**
	 * 更新人
	 */
	@ApiModelProperty(hidden = true)
	private String updateUser;
}
