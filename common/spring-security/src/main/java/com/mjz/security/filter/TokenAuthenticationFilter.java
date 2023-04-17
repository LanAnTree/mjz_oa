package com.mjz.security.filter;

import com.alibaba.fastjson.JSON;
import com.mjz.common.jwt.JwtHelper;
import com.mjz.common.result.ResponseUtil;
import com.mjz.common.result.ResultCode;
import com.mjz.common.result.ResultUtil;
import com.mjz.security.custom.LoginUserInfoHelper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description TokenAuthenticationFilter 用户token校验
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-09 14:05:29
 **/
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    public static final String NO_FILTER_LOGIN_URI = "/admin/system/index/login";

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public TokenAuthenticationFilter(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @Description {拦截请求}
     * @Date 2023/3/9 14:14
     * @param request
     * @param response
     * @param chain
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {
        //如果是登录接口，直接放行
        if(NO_FILTER_LOGIN_URI.equals(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if(null != authentication) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } else {
            ResponseUtil.out(response, ResultUtil.build(null, ResultCode.LOGIN_ERROR));
        }
    }

    /**
     * @Description {认证}
     * @Date 2023/3/9 14:23
     * @param request
     * @Return {@link UsernamePasswordAuthenticationToken}
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        //请求头是否有token
        String token = request.getHeader("mjz_oa_token");
        if(token != null && !token.isEmpty()) {
            String username = JwtHelper.getUsername(token);
            if(username != null && !username.isEmpty()) {
                //当前用户信息放到ThreadLocal里面
                LoginUserInfoHelper.setUserId(JwtHelper.getUserId(token));
                LoginUserInfoHelper.setUsername(username);

                //通过username从redis获取权限数据
                String authString = redisTemplate.opsForValue().get(username + "::authorization");
                //把redis获取字符串权限数据转换要求集合类型 List<SimpleGrantedAuthority>
                List<SimpleGrantedAuthority> authList = new ArrayList<>();
                if(authString != null && !authString.isEmpty()) {
                    List<Map> mapList = JSON.parseArray(authString, Map.class);
                    for (Map map : mapList) {
                        String authority = (String) map.get("authority");
                        authList.add(new SimpleGrantedAuthority(authority));
                    }
                }
                return new UsernamePasswordAuthenticationToken(username,null, authList);
            }
        }
        return null;
    }
}
