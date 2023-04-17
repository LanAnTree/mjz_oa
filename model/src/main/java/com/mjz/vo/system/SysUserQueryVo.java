package com.mjz.vo.system;


import lombok.Data;

import java.io.Serializable;

/**
 * @Description SysUserQueryVo 用户查询实体
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:28:44
 **/
@Data
public class SysUserQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String keyword;

	private String createTimeBegin;
	private String createTimeEnd;

	private Long roleId;
	private Long postId;
	private Long deptId;

}

