package com.mjz.auth.controller;


import com.mjz.auth.service.SysUserService;
import com.mjz.common.result.ResultUtil;
import com.mjz.common.utils.MD5;
import com.mjz.model.system.SysUser;
import com.mjz.vo.system.SysUserQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mjz
 * @since 2023-02-02
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService service;

    @ApiOperation(value = "更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public ResultUtil updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        service.updateStatus(id,status);
        return ResultUtil.success();
    }

    //用户条件分页查询
    @ApiOperation("用户条件分页查询")
    @GetMapping("{page}/{limit}")
    public ResultUtil index(@PathVariable Long page,
                        @PathVariable Long limit,
                        SysUserQueryVo sysUserQueryVo) {
        //创建page对象
        Page<SysUser> pageParam = new Page<>(page,limit);

        //封装条件，判断条件值不为空
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        //获取条件值
        String username = sysUserQueryVo.getKeyword();
        String createTimeBegin = sysUserQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysUserQueryVo.getCreateTimeEnd();
        //判断条件值不为空
        //like 模糊查询
        if(!StringUtils.isEmpty(username)) {
            wrapper.like(SysUser::getUsername,username);
        }
        //ge 大于等于
        if(!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge(SysUser::getCreateTime,createTimeBegin);
        }
        //le 小于等于
        if(!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.le(SysUser::getCreateTime,createTimeEnd);
        }

        //调用mp的方法实现条件分页查询
        IPage<SysUser> pageModel = service.page(pageParam, wrapper);
        return ResultUtil.success(pageModel);
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("get/{id}")
    public ResultUtil get(@PathVariable Long id) {
        SysUser user = service.getById(id);
        return ResultUtil.success(user);
    }

    @ApiOperation(value = "保存用户")
    @PostMapping("save")
    public ResultUtil save(@RequestBody SysUser user) {
        //密码进行加密，使用MD5
        String passwordMD5 = MD5.encrypt(user.getPassword());
        user.setPassword(passwordMD5);

        service.save(user);
        return ResultUtil.success();
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("update")
    public ResultUtil updateById(@RequestBody SysUser user) {
        service.updateById(user);
        return ResultUtil.success();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("remove/{id}")
    public ResultUtil remove(@PathVariable Long id) {
        service.removeById(id);
        return ResultUtil.success();
    }

}

