package com.ss.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TableName("sys_perm")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SysPerm extends Model<SysPerm> {
	@TableId(type= IdType.INPUT) 	// 由用户提供的权限唯一值
 	private String pval;			// 权限值，shiro的权限表达式
	private String parent;			// 父节点权限值
	private String pname;			// 权限名称
	private Integer ptype;			// 权限类型 ： 1.菜单；2.按钮
	private Boolean leaf;			// 是否是叶子节点
	private Date created;			// 权限创建时间
	private Date updated;			// 权限修改时间
	@TableField(exist = false)		// 不必须映射到数据库中去
	private List<SysPerm> children = new ArrayList<>();
}
