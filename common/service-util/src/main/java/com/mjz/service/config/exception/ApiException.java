package com.mjz.service.config.exception;

import com.mjz.common.result.BaseResult;
import com.mjz.common.result.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lucky LanAn
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @createTime 2023/3/8-14:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException{
	/**
	 * 状态码
	 */
	private int code;

	/**
	 * 状态码对应信息
	 */
	private String msg;

	public ApiException(BaseResult baseResult, String message) {
		super(message);
		this.code = baseResult.getCode();
		this.msg = baseResult.getMsg();
	}

	public ApiException(String message) {
		super(message);
		this.code = ResultCode.APP_ERROR.getCode();
		this.msg = ResultCode.APP_ERROR.getMsg();
	}
}
