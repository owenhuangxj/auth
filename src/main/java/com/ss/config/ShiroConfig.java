package com.ss.config;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	@Bean
	public Realm realm(){
		return new UserRealm();
	}

	// http://shiro.apache.org/web.html#default-filters
	// 此方式设置权限路径无效果
	@Bean
	public ShiroFilterChainDefinition shrioFilter(){
		System.out.println("========================shiroFilter========================");
		DefaultShiroFilterChainDefinition filterChain = new DefaultShiroFilterChainDefinition();
		filterChain.addPathDefinition("/auth/login","anon");
		filterChain.addPathDefinition("/auth/logout","anon");
		filterChain.addPathDefinition("/page/401","anon");
		filterChain.addPathDefinition("/page/403","anon");
		filterChain.addPathDefinition("/page/index","anon");
		filterChain.addPathDefinition("/**","authc");
		return filterChain;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 设置拦截器,LinkedHashMap内部维持了一个双向链表,可以保持顺序,能够让你取数据时，取出的数据保持进入的顺序
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		//游客，比如开发权限
		filterChainDefinitionMap.put("/auth/**", "anon");
		//其余接口一律拦截,这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		System.out.println("**********************************Shiro拦截器工厂类注入成功**********************************");
		return shiroFilterFactoryBean;
	}
	/**
	 * 注入 securityManager
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置管理器管理的域即realm.
		securityManager.setRealm(realm());
		return securityManager;
	}
}
