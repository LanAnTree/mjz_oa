package com.mjz.vo.system;

import lombok.Data;

/**
 * @Description MetaVo 路由显示信息
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:27:48
 **/
@Data
public class MetaVo
{
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    public MetaVo()
    {
    }

    public MetaVo(String title, String icon)
    {
        this.title = title;
        this.icon = icon;
    }

}

