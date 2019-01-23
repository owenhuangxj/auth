package com.ss.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ss.entity.SysRole;
import com.ss.model.AuthModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    Set<AuthModel> getRolesByUid(String uid);
}
