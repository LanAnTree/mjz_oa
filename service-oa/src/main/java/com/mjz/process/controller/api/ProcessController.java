package com.mjz.process.controller.api;

import com.mjz.auth.service.SysUserService;
import com.mjz.common.result.ResultUtil;
import com.mjz.model.process.Process;
import com.mjz.model.process.ProcessTemplate;
import com.mjz.model.process.ProcessType;
import com.mjz.process.service.OaProcessService;
import com.mjz.process.service.OaProcessTemplateService;
import com.mjz.process.service.OaProcessTypeService;
import com.mjz.vo.process.ApprovalVo;
import com.mjz.vo.process.ProcessFormVo;
import com.mjz.vo.process.ProcessVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "审批流管理")
@RestController
@RequestMapping(value="/admin/process")
@CrossOrigin //跨域
public class ProcessController {

    @Autowired
    private OaProcessTypeService processTypeService;

    @Autowired
    private OaProcessTemplateService processTemplateService;

    @Autowired
    private OaProcessService processService;

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "待处理")
    @GetMapping("/findPending/{page}/{limit}")
    public ResultUtil findPending(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        Page<Process> pageParam = new Page<>(page,limit);
        IPage<ProcessVo> pageModel = processService.findfindPending(pageParam);
        return ResultUtil.success(pageModel);
    }

    @ApiOperation(value = "启动流程")
    @PostMapping("/startUp")
    public ResultUtil startUp(@RequestBody ProcessFormVo processFormVo) {
        processService.startUp(processFormVo);
        return ResultUtil.success();
    }

    //获取审批模板数据
    @GetMapping("getProcessTemplate/{processTemplateId}")
    public ResultUtil getProcessTemplate(@PathVariable Long processTemplateId) {
        ProcessTemplate processTemplate = processTemplateService.getById(processTemplateId);
        return ResultUtil.success(processTemplate);
    }

    //查询所有审批分类和每个分类所有审批模板
    @GetMapping("findProcessType")
    public ResultUtil findProcessType() {
        List<ProcessType> list = processTypeService.findProcessType();
        return ResultUtil.success(list);
    }

    //查看审批详情信息
    @GetMapping("show/{id}")
    public ResultUtil show(@PathVariable Long id) {
        Map<String,Object> map = processService.show(id);
        return ResultUtil.success(map);
    }

    //审批
    @ApiOperation(value = "审批")
    @PostMapping("approve")
    public ResultUtil approve(@RequestBody ApprovalVo approvalVo) {
        processService.approve(approvalVo);
        return ResultUtil.success();
    }

    @ApiOperation(value = "已处理")
    @GetMapping("/findProcessed/{page}/{limit}")
    public ResultUtil findProcessed(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        Page<Process> pageParam = new Page<>(page,limit);
        IPage<ProcessVo> pageModel = processService.findProcessed(pageParam);
        return ResultUtil.success(pageModel);
    }

    @ApiOperation(value = "已发起")
    @GetMapping("/findStarted/{page}/{limit}")
    public ResultUtil findStarted(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        Page<ProcessVo> pageParam = new Page<>(page, limit);
        IPage<ProcessVo> pageModel = processService.findStarted(pageParam);
        return ResultUtil.success(pageModel);
    }

    @GetMapping("getCurrentUser")
    public ResultUtil getCurrentUser() {
        Map<String,Object> map = sysUserService.getCurrentUser();
        return ResultUtil.success(map);
    }
}
