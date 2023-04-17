package com.mjz.auth.controller;


import com.mjz.auth.service.SysMenuService;
import com.mjz.common.result.ResultUtil;
import com.mjz.model.system.SysMenu;
import com.mjz.vo.system.AssginMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author mjz
 * @since 2023-02-02
 */
@Api(tags = "菜单管理接口")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    //查询所有菜单和角色分配的菜单
    @ApiOperation("查询所有菜单和角色分配的菜单")
    @GetMapping("toAssign/{roleId}")
    public ResultUtil toAssign(@PathVariable Long roleId) {
        List<SysMenu> list = sysMenuService.findMenuByRoleId(roleId);
        return ResultUtil.success(list);
    }

    @ApiOperation("角色分配菜单")
    @PostMapping("/doAssign")
    public ResultUtil doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        sysMenuService.doAssign(assginMenuVo);
        return ResultUtil.success();
    }

    //菜单列表接口
    @ApiOperation("菜单列表")
    @GetMapping("findNodes")
    public ResultUtil findNodes() {

        List<SysMenu> list = sysMenuService.findNodes();
        return ResultUtil.success(list);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public ResultUtil save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return ResultUtil.success();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public ResultUtil updateById(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return ResultUtil.success();
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("remove/{id}")
    public ResultUtil remove(@PathVariable Long id) {
        sysMenuService.removeMenuById(id);
        return ResultUtil.success();
    }

}

