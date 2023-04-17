package com.mjz.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mjz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * @Description SysRole
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:25:10
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色名称
	 */
	@TableField("role_name")
	private String roleName;

	/**
	 * 角色编码
	 */
	@TableField("role_code")
	private String roleCode;

	/**
	 * 描述
	 */
	@TableField("description")
	private String description;
}

