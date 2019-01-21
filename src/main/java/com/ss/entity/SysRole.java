package com.ss.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@TableName("sys_role")
public class SysRole {
	@TableId(type = IdType.ID_WORKER_STR)
	private String rid;		//角色id
	private String rname;	//角色名，用于显示
	private String rdesc;	//角色描述
	private String rval;	//角色值，用于校色判断
	private Date created;	//角色创建时间
	private Date updated;	//角色修改时间
}
