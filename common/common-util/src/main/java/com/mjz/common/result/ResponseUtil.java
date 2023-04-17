package com.mjz.common.result;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lucky LanAn
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @createTime 2023/3/9-14:01
 */
public class ResponseUtil {

	public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

	/**
	 * @Description {输出}
	 * @Date 2023/3/9 14:03
	 * @param response
	 * @param resultUtil
	 */
	public static <T> void out(HttpServletResponse response, ResultUtil<T> resultUtil) {
		ObjectMapper mapper = new ObjectMapper();
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(APPLICATION_JSON_UTF8_VALUE);
		try {
			mapper.writeValue(response.getWriter(),resultUtil);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
