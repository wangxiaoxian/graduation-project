package com.bjsxt.oa.manager;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.model.Role;

public interface RoleManager {
	public void addRole(Role role);
	public void delRole(int roleId);
	public void updateRole(Role role);
	public Role findRole(int roleId);
	public PagerModel searchRoles();
}
