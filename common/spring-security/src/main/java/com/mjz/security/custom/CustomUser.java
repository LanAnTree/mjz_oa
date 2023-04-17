package com.mjz.security.custom;

import com.mjz.model.system.SysUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Description CustomUser
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-09 13:38:50
 **/
@Getter
@Setter
public class CustomUser extends User {
    private SysUser sysUser;

    /**
     * @Description {用户实体对象}
     * @Date 2023/3/9 13:38
     * @param sysUser
     * @param authorities
     * @Return {@link }
     */
    public CustomUser(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
        super(sysUser.getUsername(), sysUser.getPassword(), authorities);
        this.sysUser = sysUser;
    }
}
