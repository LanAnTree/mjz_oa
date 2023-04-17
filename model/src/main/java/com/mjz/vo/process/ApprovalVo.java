package com.mjz.vo.process;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description ApprovalVo
 * @Created with IntelliJ IDEA 2021.3.1 .
 * @Author Lucky LanAn
 * @Date 2023-03-07 22:28:21
 **/
@Data
public class ApprovalVo {

    private Long processId;

    private String taskId;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "审批描述")
    private String description;
}
