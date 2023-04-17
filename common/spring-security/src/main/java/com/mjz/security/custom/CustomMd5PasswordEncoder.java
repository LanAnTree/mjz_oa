package com.mjz.security.custom;

import com.mjz.common.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Description CustomMd5PasswordEncoder
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-09 13:37:13
 **/
@Component
public class CustomMd5PasswordEncoder implements PasswordEncoder {

    /**
     * @Description {加密}
     * @Date 2023/3/9 13:37
     * @param rawPassword
     * @Return {@link String}
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }

    /**
     * @Description {密码判断}
     * @Date 2023/3/9 13:37
     * @param rawPassword
     * @param encodedPassword
     * @Return {@link boolean}
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}
