package com.mjz.vo.system;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description SysLoginLogQueryVo
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:29:23
 **/
@Data
public class SysLoginLogQueryVo {

	@ApiModelProperty(value = "用户账号")
	private String username;

	private String createTimeBegin;
	private String createTimeEnd;

}

