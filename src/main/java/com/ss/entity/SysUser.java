package com.ss.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.ss.model.AuthModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@TableName("sys_user")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SysUser extends Model<SysUser> {
	private String uid;		// 用户id
	private String uname;	// 登陆名，不可改
	private String pwd;		// 用户昵称，可改
	private String nick;	// SHA+Salt 加密的登陆密码
	private String salt;	// 加密盐值
	private String locked;	// 是否已经锁定
	private Date created;	// 用户创建时间
	private Date updated;	// 用户信息更新时间
	private List<SysRole> roleList = new ArrayList<>();	// 用户所有角色值，在管理后天显示用户的角色
	private Set<AuthModel> roles = new HashSet<>();		// 用户所有角色值，用于shiro做校色权限的判断
	private Set<AuthModel> perms = new HashSet<>();		// 用户所有权限值，用户shiro做资源权限的判断

}
