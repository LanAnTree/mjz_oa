package com.mjz.wechat.controller;


import com.mjz.common.result.ResultUtil;
import com.mjz.vo.wechat.MenuVo;
import com.mjz.wechat.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author mjz
 * @since 2023-02-16
 */
@RestController
@RequestMapping("/admin/wechat/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("removeMenu")
    public ResultUtil removeMenu() {
        menuService.removeMenu();
        return ResultUtil.success();
    }

    @ApiOperation(value = "同步菜单")
    @GetMapping("syncMenu")
    public ResultUtil createMenu() {
        menuService.syncMenu();
        return ResultUtil.success();
    }

    @ApiOperation(value = "获取全部菜单")
    @GetMapping("findMenuInfo")
    public ResultUtil findMenuInfo() {
        List<MenuVo> menuList = menuService.findMenuInfo();
        return ResultUtil.success(menuList);
    }

}

