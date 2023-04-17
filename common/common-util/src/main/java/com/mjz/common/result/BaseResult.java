package com.mjz.common.result;

/**
 * @author Lucky LanAn
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @createTime 2023/3/8-10:49
 */
public interface BaseResult {

	/**
	 * @Description {获取状态码}
	 * @Date 2023/3/8 10:50
	 * @Return {@link Integer}
	 */
	Integer getCode();

	/**
	 * @Description {获取状态码对应信息}
	 * @Date 2023/3/8 10:50
	 * @Return {@link String}
	 */
	String getMsg();
}
