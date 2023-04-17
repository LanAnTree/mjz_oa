package com.mjz.common.jwt;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Description JwtHelper jwt工具类
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-08 17:33:25
 **/
public class JwtHelper {

    private static final Long TOKEN_EXPIRATION = 365 * 24 * 60 * 60 * 1000L;
    private static final String TOKEN_SIG_KEY = "123456";

    /**
     * @Description {根据用户id和用户名称生成token字符串}
     * @Date 2023/3/8 17:33
     * @param userId
     * @param username
     * @Return {@link String}
     */
    public static String createToken(Long userId, String username) {
        return Jwts.builder()
                //分类
                .setSubject("AUTH-USER")
                //设置token有效时长
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                //设置主体部分
                .claim("userId", userId)
                .claim("username", username)
                //签名部分
                .signWith(SignatureAlgorithm.HS512, TOKEN_SIG_KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * @Description {从生成token字符串获取用户id}
     * @Date 2023/3/8 17:34
     * @param token
     * @Return {@link Long}
     */
    public static Long getUserId(String token) {
        try {
            if (token == null || "".equals(token)) {
                return null;
            }
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(TOKEN_SIG_KEY).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Integer userId = (Integer) claims.get("userId");
            return userId.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description {从生成token字符串获取用户名称}
     * @Date 2023/3/8 17:34
     * @param token
     * @Return {@link String}
     */
    public static String getUsername(String token) {
        try {
            if (token == null || "".equals(token)) {
                return "";
            }
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(TOKEN_SIG_KEY).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String token = JwtHelper.createToken(1L, "admin");
        System.out.println(token);
        Long userId = JwtHelper.getUserId(token);
        String username = JwtHelper.getUsername(token);
        System.out.println(userId);
        System.out.println(username);
    }
}
