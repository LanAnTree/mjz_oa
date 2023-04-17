package com.mjz.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mjz.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description SysUserRole
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:26:20
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户角色")
@TableName("sys_user_role")
public class SysUserRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "角色id")
	@TableField("role_id")
	private Long roleId;

	@ApiModelProperty(value = "用户id")
	@TableField("user_id")
	private Long userId;
}

