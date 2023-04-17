package com.mjz.auth.controller;

import com.mjz.auth.service.SysMenuService;
import com.mjz.auth.service.SysUserService;
import com.mjz.common.result.ResultCode;
import com.mjz.service.config.exception.ApiException;
import com.mjz.common.jwt.JwtHelper;
import com.mjz.common.result.ResultUtil;
import com.mjz.common.utils.MD5;
import com.mjz.model.system.SysUser;
import com.mjz.vo.system.LoginVo;
import com.mjz.vo.system.RouterVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    //login
    @PostMapping("login")
    public ResultUtil login(@RequestBody LoginVo loginVo) {
        //{"code":200,"data":{"token":"admin-token"}}
//        Map<String,Object> map = new HashMap<>();
//        map.put("token","admin-token");
//        return ResultUtil.success(map);
        //1 获取输入用户名和密码
        //2 根据用户名查询数据库
        String username = loginVo.getUsername();
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,username);
        SysUser sysUser = sysUserService.getOne(wrapper);

        //3 用户信息是否存在
        if(sysUser == null) {
            throw new ApiException(ResultCode.USER_EXCEPTION,"用户不存在");
        }

        //4 判断密码
        //数据库存密码（MD5）
        String password_db = sysUser.getPassword();
        //获取输入的密码
        String password_input = MD5.encrypt(loginVo.getPassword());
        if(!password_db.equals(password_input)) {
            throw new ApiException(ResultCode.USER_EXCEPTION,"密码错误");
        }

        //5 判断用户是否被禁用  1 可用 0 禁用
        if(sysUser.getStatus().intValue()==0) {
            throw new ApiException(ResultCode.USER_EXCEPTION,"用户已经被禁用");
        }

        //6 使用jwt根据用户id和用户名称生成token字符串
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        //7 返回
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return ResultUtil.success(map);
    }

    //info
    @GetMapping("info")
    public ResultUtil info(HttpServletRequest request) {
        //1 从请求头获取用户信息（获取请求头token字符串）
        String token = request.getHeader("token");

        //2 从token字符串获取用户id 或者 用户名称
        Long userId = JwtHelper.getUserId(token);

        //3 根据用户id查询数据库，把用户信息获取出来
        SysUser sysUser = sysUserService.getById(userId);

        //4 根据用户id获取用户可以操作菜单列表
        //查询数据库动态构建路由结构，进行显示
        List<RouterVo> routerList = sysMenuService.findUserMenuListByUserId(userId);

        //5 根据用户id获取用户可以操作按钮列表
        List<String> permsList = sysMenuService.findUserPermsByUserId(userId);

        //6 返回相应的数据
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name",sysUser.getName());
        map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        //返回用户可以操作菜单
        map.put("routers",routerList);
        //返回用户可以操作按钮
        map.put("buttons",permsList);
        return ResultUtil.success(map);
    }

    @PostMapping("logout")
    public ResultUtil logout(){
        return ResultUtil.success();
    }
}
