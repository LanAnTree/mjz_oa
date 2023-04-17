package com.mjz.vo.system;

import lombok.Data;

/**
 * @Description SysOperLogQueryVo
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:29:23
 **/
@Data
public class SysOperLogQueryVo {

	private String title;
	private String operName;

	private String createTimeBegin;
	private String createTimeEnd;

}

