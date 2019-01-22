package com.ss.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ss.entity.SysRole;
import com.ss.model.AuthModel;

import java.util.Set;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    Set<AuthModel> getRolesByUid(String uid);
}
