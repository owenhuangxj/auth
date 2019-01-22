package com.ss.controller;

import com.ss.Constant.Codes;
import com.ss.model.ResultModel;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 同意处理权限相关的异常，通过JSON数据格式返回给前台，天台可以根据这个信息显示对应的提示或做页面跳转
 */
@ControllerAdvice
public class GlobalAdviceController {
	private Logger logger = LoggerFactory.getLogger(GlobalAdviceController.class);
	//不满足@RequiresGuest注解时抛出的异常信息
	private static final String GUEST_ONLY = "Attempting to perform a guest-only operation";
	@ResponseBody
	@ExceptionHandler(ShiroException.class)
	public ResultModel handleShiroException(ShiroException ex){
		String exName = ex.getClass().getSimpleName();
		logger.error("shiro执行出错 : {}",exName);
		return new ResultModel(exName,false, Codes.SHIRO_ERR,"鉴权或授权出错",null);
	}
	@ResponseBody
	@ExceptionHandler(UnauthenticatedException.class)
	public ResultModel page401(UnauthenticatedException ex){
		String exMsg = ex.getMessage();
		if(StringUtils.startsWithIgnoreCase(exMsg,GUEST_ONLY)){
			return new ResultModel("unauthenticated",false,Codes.UNAUTHEN,"只运行游客登陆，如您已经登陆请退出再访问",null).data("detail",exMsg);
		}else{
			return new ResultModel("unauthenticated",false,Codes.UNAUTHEN,"未登陆，请登陆",null).data("detail",exMsg);
		}
	}
	@ResponseBody
	@ExceptionHandler(UnauthorizedException.class)
	public ResultModel page403(UnauthorizedException ex){
		return new ResultModel("unauthorized",false,Codes.UNAUTHZ,"用户未登陆",null);
	}
}