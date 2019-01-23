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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    private String oper = "user login";
    @PostMapping("login")
    public ResultModel login(@RequestBody String body) {
        logger.info("{} body : {}", oper, body);
        JSONObject json = JSONObject.parseObject(body);
        String uname = json.getString("uname");
        String pwd = json.getString("pwd");
        logger.info("uname : {} pwd : {}" ,uname,pwd);
        if (StringUtils.isEmpty(uname)) return ResultModel.fail(oper, "用户名不能为空");
        if (StringUtils.isEmpty(pwd)) return ResultModel.fail(oper, "密码不能为空");

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(uname, pwd));
            SysUser user = (SysUser) subject.getPrincipal();
            if (null == user) throw new AuthenticationException();
            logger.info("user {} logon : sessionId : {} ", user.getUname(), subject.getSession().getId());
            return ResultModel.succ(oper)
                    .data("token", UUID.randomUUID().toString())
                    .data("roles", user.getRoles())
                    .data("perms", user.getPerms())
                    .data("uid", user.getUid())
                    .data("nick", user.getNick())
                    .data("uname", user.getUname())
                    .data("user", user);
        } catch (UnknownAccountException uae) {
            logger.warn("用户账号不正确");
            return ResultModel.fail(oper, "账号或密码不正确");
        } catch (IncorrectCredentialsException ice) {
            logger.warn("用户密码不正确");
            return ResultModel.fail(oper, "账号或密码不正确");
        } catch (LockedAccountException lae) {
            logger.warn("账号被锁定");
            return ResultModel.fail(oper, "账号已经被锁定");
        } catch (AuthenticationException ae) {
            logger.warn("登录出错");
            return ResultModel.fail(oper,"登录失败：".concat(ae.getMessage()));
        }
    }

    /**
     * 获取
     */
    @PostMapping("info")
    public ResultModel info(){
        String oper = "get user info";
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        logger.info("{} sessionId : {}",oper,subject.getSession().getId());
        return ResultModel.succ(oper)
                    .data("name",user.getUname())
                    .data("nick",user.getNick())
                    .data("roles",user.getRoles())
                    .data("perms",user.getPerms());
    }

    /**
     * 登出的方法
     * @return
     */
    @PostMapping("logout")
    public ResultModel logout(){
        String oper = "user logout";
        logger.info("{}",oper);
        SecurityUtils.getSubject().logout();
        return ResultModel.succ(oper);
    }
}
