package com.mjz.common.result;

import lombok.AllArgsConstructor;

/**
 * @author Lucky LanAn
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @createTime 2023/3/8-10:49
 */
@AllArgsConstructor
public enum ResultCode implements BaseResult {
	/**
	 * 状态
	 */
	SUCCESS(200,"请求成功"),
	FAILED(400, "请求失败"),
	VALIDATE_ERROR(1002,"参数校验失败"),
	RESPONSE_PACK_ERROR(1003, "response返回包装失败"),
	APP_ERROR(2000, "业务异常"),
	PRICE_ERROR(2001, "价格异常"),
	USER_EXCEPTION(2003, "用户信息异常"),
	OPERATION_INVALID(2004, "操作无效"),
	LOGIN_ERROR(1001, "登录异常");

	/**
	 * 状态码
	 */
	private final int code;

	/**
	 * 状态码对应信息
	 */
	private final String msg;


	/**
	 * @Description {获取状态码}
	 * @Date 2023/3/8 10:50
	 * @Return {@link Integer}
	 */
	@Override
	public Integer getCode() {
		return this.code;
	}

	/**
	 * @Description {获取状态码对应信息}
	 * @Date 2023/3/8 10:50
	 * @Return {@link String}
	 */
	@Override
	public String getMsg() {
		return this.msg;
	}
}
