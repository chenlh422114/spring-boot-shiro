package com.leigq.www.shiro.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 *
 * </p>
 *
 * @author leigq
 * @since 2019-06-28
 */
@TableName("sys_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    @TableField("permissionId")
    private Integer permissionId;

    @TableField("roleId")
    private Integer roleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


}
