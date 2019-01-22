package com.ss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ss.entity.SysRole;
import com.ss.model.AuthModel;

import java.util.Set;

public interface SysRoleService extends IService<SysRole> {
    Set<AuthModel> getRolesByUid(String uid);
}
