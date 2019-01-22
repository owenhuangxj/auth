package com.ss.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ss.entity.SysPerm;
import com.ss.model.AuthModel;

import java.util.Set;

public interface SysPermMapper extends BaseMapper<SysPerm> {
    Set<AuthModel> getPermsByUid(String uid);
}
