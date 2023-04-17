package com.mjz.model.process;

import com.mjz.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Description ProcessType
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:24:26
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "ProcessType")
@TableName("oa_process_type")
public class ProcessType extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "类型名称")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "描述")
	@TableField("description")
	private String description;

	@TableField(exist = false)
	private List<ProcessTemplate> processTemplateList;
}
