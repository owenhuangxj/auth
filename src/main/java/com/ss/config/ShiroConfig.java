package com.ss.config;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
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
	@Bean
	public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		/**
		 * 用于解决无法识别@RequiresPermissons和@RequiresRoles注解
		 */
		creator.setProxyTargetClass(true);
		return creator;
	}
	// http://shiro.apache.org/web.html#default-filters
//	@Bean
//	public ShiroFilterChainDefinition shrioFilter(){
//		DefaultShiroFilterChainDefinition filterChain = new DefaultShiroFilterChainDefinition();
//		filterChain.addPathDefinition("/auth/login","anon");
//		filterChain.addPathDefinition("/auth/logout","anon");
//		filterChain.addPathDefinition("/page/401","anon");
//		filterChain.addPathDefinition("/page/403","anon");
//		filterChain.addPathDefinition("/page/index","anon");
//		filterChain.addPathDefinition("/**","authc");
//		return filterChain;
//	}
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     *  ShiroFilterFactoryBean 用于配置Shiro的Filter，Shiro的Filter用来规定权限认证的规则
     * @param securityManager
     * @return
     */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
		shiroFilterFactoryBean.setLoginUrl("/login");

		// 设置拦截器,LinkedHashMap内部维持了一个双向链表,可以保持顺序,能够让你取数据时，取出的数据保持进入的顺序
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		//auth路径下的所有请求都不需要身份验证
		filterChainDefinitionMap.put("/auth/**", "anon");
		//
//		filterChainDefinitionMap.put("/manager/**","roles[root]");
//		filterChainDefinitionMap.put("/user/**","roles[user]");

		//其余接口一律拦截,这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	/**
	 * 向spring容器注入 securityManager ，如果采用如下的的方式引入shiro就不需要手动注入了，因为springboot已经自动注入了改bean
	 * 	<dependency>
	 * 		<groupId>org.apache.shiro</groupId>
	 * 		<artifactId>shiro-spring-boot-web-starter</artifactId>
	 * 		<version>1.4.0-RC2</version>
	 * 	</dependency>
	 * */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置管理器管理的域即realm.
		securityManager.setRealm(realm());
		return securityManager;
	}
	@Bean
	public ShiroDialect shiroDialect(){
		return new ShiroDialect();
	}
}
