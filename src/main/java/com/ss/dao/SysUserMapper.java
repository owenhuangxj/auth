package com.ss.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ss.entity.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser getUserRoles();
}
