package com.ss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ss.dao.SysRoleMapper;
import com.ss.entity.SysRole;
import com.ss.model.AuthModel;
import com.ss.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public Set<AuthModel> getRolesByUid(String uid) {
        return baseMapper.getRolesByUid(uid);
    }
}
