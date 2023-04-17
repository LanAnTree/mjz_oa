package com.mjz.process.service;

import com.mjz.model.process.ProcessRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 审批记录 服务类
 * </p>
 *
 * @author mjz
 * @since 2023-02-15
 */
public interface OaProcessRecordService extends IService<ProcessRecord> {

    void record(Long processId,Integer status,String description);
}
