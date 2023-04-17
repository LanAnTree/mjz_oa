package com.mjz.wechat.service;

import com.mjz.model.wechat.Menu;
import com.mjz.vo.wechat.MenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author mjz
 * @since 2023-02-16
 */
public interface MenuService extends IService<Menu> {

    //获取全部菜单
    List<MenuVo> findMenuInfo();

    //同步菜单
    void syncMenu();

    //删除菜单
    void removeMenu();
}
