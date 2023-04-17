package com.mjz.vo.system;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description LoginVo
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:29:23
 **/
@Data
public class LoginVo {

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不可为空")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码不可为空")
    private String password;
}
