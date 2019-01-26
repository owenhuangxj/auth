package com.ss.service.impl;

import com.ss.model.ResultModel;
import com.ss.service.ManagerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    public ResultModel info(){
        return ResultModel.succ("get info").data("info", SecurityUtils.getSubject().getPrincipal());
    }
}
