package com.mjz.security.custom;

/**
 * @Description LoginUserInfoHelper
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-09 13:41:28
 **/
public class LoginUserInfoHelper {

    private static final ThreadLocal<Long> USER_ID = new ThreadLocal<>();
    private static final ThreadLocal<String> USER_NAME = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        USER_ID.set(userId);
    }
    public static Long getUserId() {
        return USER_ID.get();
    }
    public static void removeUserId() {
        USER_ID.remove();
    }
    public static void setUsername(String username) {
        USER_NAME.set(username);
    }
    public static String getUsername() {
        return USER_NAME.get();
    }
    public static void removeUsername() {
        USER_NAME.remove();
    }
}
