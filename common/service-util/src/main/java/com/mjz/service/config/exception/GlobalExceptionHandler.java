package com.mjz.service.config.exception;

import com.mjz.common.result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 日志打印
     */
    private static final Logger LOGGER =  LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @Description {权限}
     * @Date 2023/3/9 16:56
     * @param e
     * @Return {@link ResultUtil<String>}
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResultUtil<String> error(AccessDeniedException e) {
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.fail(205, "权限不够");
    }

    /**
     * @Description {特定异常处理}
     * @Date 2023/3/8 14:39
     * @param e
     * @Return {@link ResultUtil}
     */
    @ExceptionHandler(ArithmeticException.class)
    public ResultUtil<String> error(ArithmeticException e) {
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.fail("执行特定异常处理...");
    }

    /**
     * @Description {自定义异常处理}
     * @Date 2023/3/8 14:41
     * @param e
     * @Return {@link ResultUtil}
     */
    @ExceptionHandler(ApiException.class)
    public ResultUtil<String> error(ApiException e) {
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.fail(e.getCode(), e.getMsg(), e.getMessage());
    }

    /**
     * @Description {全局异常处理，执行的方法}
     * @Date 2023/3/8 14:39
     * @param e
     * @Return {@link ResultUtil}
     */
    @ExceptionHandler(Exception.class)
    public ResultUtil<String> error(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.fail("执行全局异常处理...");
    }
    // spring security异常
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseBody
//    public ResultUtil error(AccessDeniedException e) throws AccessDeniedException {
//        return ResultUtil.fail().code(205).message("没有操作权限");
//    }
}
