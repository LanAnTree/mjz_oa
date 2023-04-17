//
//
package com.mjz.vo.system;

import java.io.Serializable;

/**
 * @Description SysRoleQueryVo 角色查询实体
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:28:30
 **/
public class SysRoleQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}

