package com.mjz.process.controller;


import com.mjz.common.result.ResultUtil;
import com.mjz.model.process.ProcessType;
import com.mjz.process.service.OaProcessTypeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 审批类型 前端控制器
 * </p>
 *
 * @author mjz
 * @since 2023-02-14
 */
@RestController
@RequestMapping(value = "/admin/process/processType")
public class OaProcessTypeController {

    @Autowired
    private OaProcessTypeService processTypeService;

    //查询所有审批分类
    @GetMapping("findAll")
    public ResultUtil findAll() {
        List<ProcessType> list = processTypeService.list();
        return ResultUtil.success(list);
    }

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public ResultUtil index(@PathVariable Long page,
                        @PathVariable Long limit) {
        Page<ProcessType> pageParam = new Page<>(page,limit);
        IPage<ProcessType> pageModel = processTypeService.page(pageParam);
        return ResultUtil.success(pageModel);
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public ResultUtil get(@PathVariable Long id) {
        ProcessType processType = processTypeService.getById(id);
        return ResultUtil.success(processType);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public ResultUtil save(@RequestBody ProcessType processType) {
        processTypeService.save(processType);
        return ResultUtil.success();
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public ResultUtil updateById(@RequestBody ProcessType processType) {
        processTypeService.updateById(processType);
        return ResultUtil.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public ResultUtil remove(@PathVariable Long id) {
        processTypeService.removeById(id);
        return ResultUtil.success();
    }
}

