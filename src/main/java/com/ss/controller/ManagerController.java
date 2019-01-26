package com.ss.controller;

import com.ss.model.ResultModel;
import com.ss.service.ManagerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @RequestMapping("/info")
//    @RequiresRoles("root")
//    @RequiresPermissions("aaa:kkk")
    public ResultModel info(){
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ get user's info /////////////////////////////////");
        return managerService.info();
//        return ResultModel.succ("get info").data("info", SecurityUtils.getSubject().getPrincipal());
    }
    @Autowired
    private ManagerService managerService;
}
