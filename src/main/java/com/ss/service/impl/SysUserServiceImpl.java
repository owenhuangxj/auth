package com.ss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ss.dao.SysUserMapper;
import com.ss.entity.SysUser;
import com.ss.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 *  ServiceImpl 实现了 Iservice的所有方法
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
