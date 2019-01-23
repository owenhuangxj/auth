package com.ss.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ss.entity.SysPerm;
import com.ss.model.AuthModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;
@Mapper
public interface SysPermMapper extends BaseMapper<SysPerm> {
    Set<AuthModel> getPermsByUid(String uid);
}
