package com.mjz.security.filter;

import com.alibaba.fastjson.JSON;
import com.mjz.common.jwt.JwtHelper;
import com.mjz.common.result.ResponseUtil;
import com.mjz.common.result.ResultCode;
import com.mjz.common.result.ResultUtil;
import com.mjz.security.custom.CustomUser;
import com.mjz.vo.system.LoginVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TokenLoginFilter 登录
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-09 14:07:03
 **/
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    public static final String NO_FILTER_LOGIN_URI = "/admin/system/index/login";

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * @Description {构造方法}
     * @Date 2023/3/9 13:52
     * @param authenticationManager
     * @param redisTemplate
     * @Return {@link }
     */
    public TokenLoginFilter(AuthenticationManager authenticationManager,
                            RedisTemplate<String, String> redisTemplate) {
        this.setAuthenticationManager(authenticationManager);
        this.setPostOnly(false);
        //指定登录接口及提交方式，可以指定任意路径
        this.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher(NO_FILTER_LOGIN_URI,"POST"));
        this.redisTemplate = redisTemplate;
    }


    /**
     * @Description {登录认证}
     * @Date 2023/3/9 13:53
     * @param request
     * @param response
     * @Return {@link Authentication}
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        try {
            //获取用户信息
            LoginVo loginVo = new ObjectMapper().readValue(request.getInputStream(), LoginVo.class);
            //封装对象
            Authentication authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
            //调用方法
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description {认证成功调用方法}
     * @Date 2023/3/9 13:53
     * @param request
     * @param response
     * @param chain
     * @param auth
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        //获取当前用户
        CustomUser customUser = (CustomUser)auth.getPrincipal();
        //生成token
        String token = JwtHelper.createToken(customUser.getSysUser().getId(),
                customUser.getSysUser().getUsername());

        //获取当前用户权限数据，放到Redis里面 key：username   value：权限数据
        redisTemplate.opsForValue().set(customUser.getUsername() + "::authorization",
                JSON.toJSONString(customUser.getAuthorities()));

        //返回
        Map<String,Object> map = new HashMap<>(2);
        map.put("mjz_oa_token",token);
        ResponseUtil.out(response, ResultUtil.success(map));
    }

    /**
     * @Description {认证失败调用方法}
     * @Date 2023/3/9 13:59
     * @param request
     * @param response
     * @param failed
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {
        ResponseUtil.out(response, ResultUtil.build(null, ResultCode.LOGIN_ERROR));
    }
}
