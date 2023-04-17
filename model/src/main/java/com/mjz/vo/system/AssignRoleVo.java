package com.mjz.vo.system;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description AssignRoleVo
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:29:13
 **/
@ApiModel(description = "分配菜单")
@Data
public class AssignRoleVo {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "角色id列表")
    private List<Long> roleIdList;

}
