package com.mjz.vo.system;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description AssginMenuVo
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:29:01
 **/
@ApiModel(description = "分配菜单")
@Data
public class AssginMenuVo {

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "菜单id列表")
    private List<Long> menuIdList;

}
