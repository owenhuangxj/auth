package com.ss.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

	@Bean
	public Realm realm(){
		return new UserRealm();
	}

	// http://shiro.apache.org/web.html#default-filters
	@Bean
	public ShiroFilterChainDefinition shrioFilter(){
		DefaultShiroFilterChainDefinition filterChain = new DefaultShiroFilterChainDefinition();
		filterChain.addPathDefinition("/auth/login","anon");
		filterChain.addPathDefinition("/auth/logout","anon");
		filterChain.addPathDefinition("/page/401","anon");
		filterChain.addPathDefinition("/page/403","anon");
		filterChain.addPathDefinition("/page/index","anon");
		filterChain.addPathDefinition("/**","authc");
		return filterChain;
	}
}
