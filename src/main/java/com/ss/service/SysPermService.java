package com.ss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ss.entity.SysPerm;
import com.ss.model.AuthModel;

import java.util.Set;

public interface SysPermService extends IService<SysPerm> {
    Set<AuthModel> getPermsByUid(String uid);
}
