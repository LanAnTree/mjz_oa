package com.mjz.vo.process;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description ProcessQueryVo
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:27:29
 **/
@Data
@ApiModel(description = "Process")
public class ProcessQueryVo {

	@ApiModelProperty(value = "关键字")
	private String keyword;

	@ApiModelProperty(value = "用户id")
	private Long userId;

	@TableField("process_template_id")
	private Long processTemplateId;

	@ApiModelProperty(value = "审批类型id")
	private Long processTypeId;

	private String createTimeBegin;
	private String createTimeEnd;

	@ApiModelProperty(value = "状态（0：默认 1：审批中 2：审批通过 -1：驳回）")
	private Integer status;


}
