package com.mjz.auth.controller;

import com.mjz.auth.service.SysRoleService;
import com.mjz.service.config.exception.ApiException;
import com.mjz.common.result.ResultUtil;
import com.mjz.model.system.SysRole;
import com.mjz.vo.system.AssignRoleVo;
import com.mjz.vo.system.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    // http://localhost:8800/admin/system/sysRole/findAll

    //注入service
    @Autowired
    private SysRoleService sysRoleService;

    //1 查询所有角色 和 当前用户所属角色
    @ApiOperation("获取角色")
    @GetMapping("/toAssign/{userId}")
    public ResultUtil toAssign(@PathVariable Long userId) {
        Map<String,Object> map = sysRoleService.findRoleDataByUserId(userId);
        return ResultUtil.success(map);
    }

    //2 为用户分配角色
    @ApiOperation("为用户分配角色")
    @PostMapping("/doAssign")
    public ResultUtil doAssign(@RequestBody AssignRoleVo vo) {
        sysRoleService.doAssign(vo);
        return ResultUtil.success();
    }

    //查询所有角色
//    @GetMapping("/findAll")
//    public List<SysRole> findAll() {
//        //调用service的方法
//        List<SysRole> list = sysRoleService.list();
//        return list;
//    }

    //统一返回数据结果
    @ApiOperation("查询所有角色")
    @GetMapping("/findAll")
    public ResultUtil findAll() {
        //调用service的方法
        List<SysRole> list = sysRoleService.list();

        return ResultUtil.success(list);
    }

    //条件分页查询
    //page 当前页  limit 每页显示记录数
    //SysRoleQueryVo 条件对象
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public ResultUtil pageQueryRole(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysRoleQueryVo sysRoleQueryVo) {
        //调用service的方法实现
        //1 创建Page对象，传递分页相关参数
        //page 当前页  limit 每页显示记录数
        Page<SysRole> pageParam = new Page<>(page,limit);

        //2 封装条件，判断条件是否为空，不为空进行封装
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if(!StringUtils.isEmpty(roleName)) {
            //封装 like模糊查询
            wrapper.like(SysRole::getRoleName,roleName);
        }

        //3 调用方法实现
        IPage<SysRole> pageModel = sysRoleService.page(pageParam, wrapper);
        return ResultUtil.success(pageModel);
    }

    //添加角色
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation("添加角色")
    @PostMapping ("save")
    public ResultUtil save(@RequestBody SysRole role) {
        //调用service的方法
        boolean is_success = sysRoleService.save(role);
        if(is_success) {
            return ResultUtil.success();
        } else {
            return ResultUtil.fail();
        }
    }

    //修改角色-根据id查询
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("根据id查询")
    @GetMapping("get/{id}")
    public ResultUtil get(@PathVariable Long id) {
        SysRole sysRole = sysRoleService.getById(id);
        return ResultUtil.success(sysRole);
    }

    //修改角色-最终修改
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation("修改角色")
    @PutMapping ("update")
    public ResultUtil update(@RequestBody SysRole role) {
        //调用service的方法
        boolean is_success = sysRoleService.updateById(role);
        if(is_success) {
            return ResultUtil.success();
        } else {
            return ResultUtil.fail();
        }
    }

    //根据id删除
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("根据id删除")
    @DeleteMapping("remove/{id}")
    public ResultUtil remove(@PathVariable Long id) {
        boolean is_success = sysRoleService.removeById(id);
        if(is_success) {
            return ResultUtil.success();
        } else {
            return ResultUtil.fail();
        }
    }

    //批量删除
    // 前端数组 [1,2,3]
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public ResultUtil batchRemove(@RequestBody List<Long> idList) {
        boolean is_success = sysRoleService.removeByIds(idList);
        if(is_success) {
            return ResultUtil.success();
        } else {
            return ResultUtil.fail();
        }
    }
}
