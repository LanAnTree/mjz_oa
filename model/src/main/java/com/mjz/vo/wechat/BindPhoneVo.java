package com.mjz.vo.wechat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description BindPhoneVo
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:29:05
 **/
@Data
public class BindPhoneVo {

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "openId")
    private String openId;
}
