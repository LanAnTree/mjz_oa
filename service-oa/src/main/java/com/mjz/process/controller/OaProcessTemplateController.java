package com.mjz.process.controller;


import com.mjz.common.result.ResultUtil;
import com.mjz.model.process.ProcessTemplate;
import com.mjz.process.service.OaProcessTemplateService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 审批模板 前端控制器
 * </p>
 *
 * @author mjz
 * @since 2023-02-14
 */
@RestController
@RequestMapping(value = "/admin/process/processTemplate")
public class OaProcessTemplateController {

    @Autowired
    private OaProcessTemplateService processTemplateService;

    //分页查询审批模板
    @ApiOperation("获取分页审批模板数据")
    @GetMapping("{page}/{limit}")
    public ResultUtil index(@PathVariable Long page,
                        @PathVariable Long limit) {
        Page<ProcessTemplate> pageParam = new Page<>(page,limit);
        //分页查询审批模板，把审批类型对应名称查询
        IPage<ProcessTemplate> pageModel =
                processTemplateService.selectPageProcessTempate(pageParam);
        return ResultUtil.success(pageModel);
    }

    //@PreAuthorize("hasAuthority('bnt.processTemplate.list')")
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public ResultUtil get(@PathVariable Long id) {
        ProcessTemplate processTemplate = processTemplateService.getById(id);
        return ResultUtil.success(processTemplate);
    }

    //@PreAuthorize("hasAuthority('bnt.processTemplate.templateSet')")
    @ApiOperation(value = "新增")
    @PostMapping("save")
    public ResultUtil save(@RequestBody ProcessTemplate processTemplate) {
        processTemplateService.save(processTemplate);
        return ResultUtil.success();
    }

    //@PreAuthorize("hasAuthority('bnt.processTemplate.templateSet')")
    @ApiOperation(value = "修改")
    @PutMapping("update")
    public ResultUtil updateById(@RequestBody ProcessTemplate processTemplate) {
        processTemplateService.updateById(processTemplate);
        return ResultUtil.success();
    }

    //@PreAuthorize("hasAuthority('bnt.processTemplate.remove')")
    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public ResultUtil remove(@PathVariable Long id) {
        processTemplateService.removeById(id);
        return ResultUtil.success();
    }

    @ApiOperation(value = "上传流程定义")
    @PostMapping("/uploadProcessDefinition")
    public ResultUtil uploadProcessDefinition(MultipartFile file) throws FileNotFoundException {
        //获取classes目录位置
        String path = new File(ResourceUtils.getURL("classpath:")
                          .getPath()).getAbsolutePath();
        //设置上传文件夹
        File tempFile = new File(path + "/processes/");
        if(!tempFile.exists()) {
            tempFile.mkdirs();
        }
        //创建空文件，实现文件写入
        String filename = file.getOriginalFilename();
        File zipFile = new File(path + "/processes/" + filename);

        //保存文件
        try {
            file.transferTo(zipFile);
        } catch (IOException e) {
            return ResultUtil.fail();
        }

        Map<String, Object> map = new HashMap<>();
        //根据上传地址后续部署流程定义，文件名称为流程定义的默认key
        map.put("processDefinitionPath", "processes/" + filename);
        map.put("processDefinitionKey", filename.substring(0, filename.lastIndexOf(".")));
        return ResultUtil.success(map);
    }

    //部署流程定义（发布）
    @ApiOperation(value = "发布")
    @GetMapping("/publish/{id}")
    public ResultUtil publish(@PathVariable Long id) {
        //修改模板发布状态 1 已经发布
        //流程定义部署
        processTemplateService.publish(id);
        return ResultUtil.success();
    }

    public static void main(String[] args) {
        try {
            String path = new File(ResourceUtils.getURL("classpath:")
                    .getPath()).getAbsolutePath();
            System.out.println(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

