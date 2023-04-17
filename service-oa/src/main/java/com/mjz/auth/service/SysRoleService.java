package com.mjz.auth.service;

import com.mjz.model.system.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mjz.vo.system.AssignRoleVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {

    //1 查询所有角色 和 当前用户所属角色
    Map<String, Object> findRoleDataByUserId(Long userId);

    //2 为用户分配角色
    void doAssign(AssignRoleVo vo);
}
