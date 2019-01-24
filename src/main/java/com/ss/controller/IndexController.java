package com.ss.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@GetMapping("login")
	public String login(){
		return "login";
	}

	@GetMapping("/page/401")
	public String page401(){
		return "login";
	}
}
