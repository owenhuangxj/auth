package com.ss.controller;

import com.alibaba.fastjson.JSONObject;
import com.ss.entity.SysUser;
import com.ss.model.ResultModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/auth")
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    private String oper = "user login";
    @PostMapping("/login")
    public String login(@RequestParam("uname") String uname, @RequestParam("pwd") String pwd, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(uname, pwd);
        token.setRememberMe(true);
        subject.login(token);
        SysUser user = (SysUser) subject.getPrincipal();
        if (null == user) throw new AuthenticationException();
        logger.info("principal : {}", (SysUser)SecurityUtils.getSubject().getPrincipal());
        return "index";
    }
}
