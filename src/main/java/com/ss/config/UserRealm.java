package com.ss.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ss.entity.SysUser;
import com.ss.model.AuthModel;
import com.ss.service.SysPermService;
import com.ss.service.SysRoleService;
import com.ss.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Realm 領域，與數據庫進行交互，定义了如何查询用户信息，如何查询用户的角色和权限，如何校验密码等逻辑
 */
public class UserRealm extends AuthorizingRealm {
	@Autowired
	private SysUserService userService;
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private SysPermService permService;
	/**
	 * 配置密码验证的规则，shiro根据这个规则进行密码的验证
	 *  credential : 凭据
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
		hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME); //SHA-256
		hashMatcher.setStoredCredentialsHexEncoded(false);
		hashMatcher.setHashIterations(1024);
		super.setCredentialsMatcher(hashMatcher);
	}

	/**
	 * shiro会调用该方法来鉴权
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("======================== doGetAuthenticationInfo ========================");
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String uname = token.getUsername();
		if(uname == null) throw new AccountException("用户名不能为空"); // 防止通过api进行登录而非前端页面的登录
		SysUser user = userService.getOne(new QueryWrapper<SysUser>().eq("uname",uname));
		if(user == null) throw new UnknownAccountException("找不到 ".concat(uname).concat(" 的相关信息！"));
		Set<AuthModel> roles = roleService.getRolesByUid(user.getUid());
		Set<AuthModel> perms = permService.getPermsByUid(user.getUid());
		user.getRoles().addAll(roles);
		user.getPerms().addAll(perms);
		/**
		 * 此处是将用户的相关信息通过AuthenticationInfo接口进行保存，以便其它地方可以进行获取，比如doGetAuthorizationInfo方法中
		 * 在任何地方通过SecurityUtils.getSubject().getPrincipal()都能拿出用户的所有信息，包括角色和权限
		 * 此处的SimpleAuthenticationInfo(Object principal, Object credentials, String realmName)的第一个参数传入的是SysUser,
		 * 第二个是 SysUser 的password
 		 */
		System.out.println("========================== ".concat(getName()).concat(" =========================="));
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPwd(),getName());
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
		return authenticationInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("======================== doGetAuthorizationInfo ========================");
		SysUser user = (SysUser) getAvailablePrincipal(principalCollection);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<AuthModel> roles = user.getRoles();
		Set<AuthModel> perms = user.getPerms();
		authorizationInfo.setRoles(roles.stream().map(AuthModel::getVal).collect(Collectors.toSet()));
		authorizationInfo.setStringPermissions(perms.stream().map(AuthModel::getVal).collect(Collectors.toSet()));
		return authorizationInfo;
	}
}
