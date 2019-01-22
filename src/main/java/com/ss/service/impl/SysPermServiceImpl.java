package com.ss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ss.dao.SysPermMapper;
import com.ss.entity.SysPerm;
import com.ss.model.AuthModel;
import com.ss.service.SysPermService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SysPermServiceImpl extends ServiceImpl<SysPermMapper, SysPerm> implements SysPermService {
    @Override
    public Set<AuthModel> getPermsByUid(String uid) {
        return baseMapper.getPermsByUid(uid);
    }
}
