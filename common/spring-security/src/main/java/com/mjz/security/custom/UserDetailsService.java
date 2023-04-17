package com.mjz.security.custom;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Description UserDetailsService
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-09 13:42:05
 **/
@Component
public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

    /**
     * @Description {根据用户名获取用户对象（获取不到直接抛异常）}
     * @Date 2023/3/9 13:42
     * @param username
     * @Return {@link UserDetails}
     */
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
