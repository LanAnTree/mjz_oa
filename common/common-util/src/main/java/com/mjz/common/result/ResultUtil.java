package com.mjz.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lucky LanAn
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @createTime 2023/3/8-10:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultUtil<T> {
	/**
	 * 返回状态码
	 */
	private Integer code;

	/**
	 * 返回信息
	 */
	private String message;

	/**
	 * 返回数据
	 */
	private T data;

	/**
	 * 请求地址
	 */
	private String url;

	public ResultUtil(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * @Description {封装返回是数据}
	 * @Date 2023/3/9 14:05
	 * @param body
	 * @param resultCode
	 * @Return {@link ResultUtil<T>}
	 */
	public static <T> ResultUtil<T> build(T body, ResultCode resultCode) {
		ResultUtil<T> result = new ResultUtil<>();
		//封装数据
		if(body != null) {
			result.setData(body);
		}
		//状态码
		result.setCode(resultCode.getCode());
		//返回信息
		result.setMessage(resultCode.getMsg());
		return result;
	}

	/**
	 * @Description {返回 ResultCode 中状态码和信息}
	 * @Date 2023/3/7 17:18
	 * @Return {@link ResultUtil<T>}
	 */
	public static <T> ResultUtil<T> success() {
		ResultUtil<T> resultUtil = new ResultUtil<>();
		resultUtil.setCode(ResultCode.SUCCESS.getCode());
		resultUtil.setMessage(ResultCode.SUCCESS.getMsg());
		return resultUtil;
	}

	/**
	 * @Description {返回数据}
	 * @Date 2023/3/7 17:19
	 * @param data 返回数据
	 * @Return {@link ResultUtil<T>}
	 */
	public static <T> ResultUtil<T> success(T data) {
		ResultUtil<T> resultUtil = success();
		resultUtil.setData(data);
		return resultUtil;
	}

	/**
	 * @Description {返回 ResultCode 中信息，以及数据}
	 * @Date 2023/3/7 17:20
	 * @param message
	 * @param data
	 * @Return {@link ResultUtil<T>}
	 */
	public static <T> ResultUtil<T> success(String message, T data) {
		ResultUtil<T> resultUtil = success();
		resultUtil.setMessage(message);
		resultUtil.setData(data);
		return resultUtil;
	}

	/**
	 * @Description {返回 自定义 中状态码和信息，以及数据}
	 * @Date 2023/3/7 17:21
	 * @param code
	 * @param message
	 * @param data
	 * @Return {@link ResultUtil<T>}
	 */
	public static <T> ResultUtil<T> success(Integer code, String message, T data) {
		ResultUtil<T> resultUtil = new ResultUtil<>();
		resultUtil.setCode(code);
		resultUtil.setMessage(message);
		resultUtil.setData(data);
		return resultUtil;
	}

	/**
	 * @Description {返回 自定义 中状态码和自定义信息，以及URI}
	 * @Date 2023/3/7 17:21
	 * @param request
	 * @param message
	 * @Return {@link ResultUtil<T>}
	 */
	public static <T> ResultUtil<T> success(HttpServletRequest request, String message) {
		ResultUtil<T> resultUtil = new ResultUtil<>();
		resultUtil.setCode(ResultCode.SUCCESS.getCode());
		resultUtil.setMessage(message);
		resultUtil.setUrl(request.getRequestURI());
		return resultUtil;
	}

	/**
	 * @Description {返回 自定义 中状态码和信息，以及URI}
	 * @Date 2023/3/7 17:22
	 * @param request
	 * @Return {@link ResultUtil<T>}
	 */
	public static <T> ResultUtil<T> success(HttpServletRequest request) {
		ResultUtil<T> resultUtil = new ResultUtil<>();
		resultUtil.setCode(ResultCode.SUCCESS.getCode());
		resultUtil.setMessage(ResultCode.SUCCESS.getMsg());
		resultUtil.setUrl(request.getRequestURI());
		return resultUtil;
	}

	/**
	 * @Description {返回 自定义 中状态码和信息，以及数据和URI}
	 * @Date 2023/3/7 17:22
	 * @param request
	 * @param data
	 * @Return {@link ResultUtil<T>}
	 */
	public static <T> ResultUtil<T> success(HttpServletRequest request,T data) {
		ResultUtil<T> resultUtil = new ResultUtil<>();
		resultUtil.setCode(ResultCode.SUCCESS.getCode());
		resultUtil.setMessage(ResultCode.SUCCESS.getMsg());
		resultUtil.setData(data);
		resultUtil.setUrl(request.getRequestURI());
		return resultUtil;
	}

	public static <T> ResultUtil<T> fail() {
		ResultUtil<T> resultUtil = new ResultUtil<>();
		resultUtil.setCode(ResultCode.FAILED.getCode());
		resultUtil.setMessage(ResultCode.FAILED.getMsg());
		return resultUtil;
	}

	public static <T> ResultUtil<T> fail(T data) {
		ResultUtil<T> resultUtil = fail();
		resultUtil.setData(data);
		return resultUtil;
	}

	public static <T> ResultUtil<T> fail(String message, T data) {
		ResultUtil<T> resultUtil = fail();
		resultUtil.setMessage(message);
		resultUtil.setData(data);
		return resultUtil;
	}

	public static <T> ResultUtil<T> fail(Integer code, String message) {
		ResultUtil<T> resultUtil = fail();
		resultUtil.setCode(code);
		resultUtil.setMessage(message);
		return resultUtil;
	}

	public static <T> ResultUtil<T> fail(Integer code, String message, T data) {
		ResultUtil<T> resultUtil = new ResultUtil<>();
		resultUtil.setCode(code);
		resultUtil.setMessage(message);
		resultUtil.setData(data);
		return resultUtil;
	}

	public static <T> ResultUtil<T> fail(HttpServletRequest request) {
		ResultUtil<T> resultUtil = new ResultUtil<>();
		resultUtil.setCode(ResultCode.FAILED.getCode());
		resultUtil.setMessage(ResultCode.FAILED.getMsg());
		resultUtil.setUrl(request.getRequestURI());
		return resultUtil;
	}

	public static <T> ResultUtil<T> fail(HttpServletRequest request,String message) {
		ResultUtil<T> resultUtil = new ResultUtil<>();
		resultUtil.setCode(ResultCode.FAILED.getCode());
		resultUtil.setMessage(message);
		resultUtil.setUrl(request.getRequestURI());
		return resultUtil;
	}


	public static <T> ResultUtil<T> fail(HttpServletRequest request,T data) {
		ResultUtil<T> resultUtil = new ResultUtil<>();
		resultUtil.setCode(ResultCode.FAILED.getCode());
		resultUtil.setMessage(ResultCode.FAILED.getMsg());
		resultUtil.setData(data);
		resultUtil.setUrl(request.getRequestURI());
		return resultUtil;
	}
}
