package com.bjsxt.oa.web;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.RoleManager;
import com.bjsxt.oa.model.Role;
import com.opensymphony.xwork2.ActionSupport;

public class RoleAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private RoleManager roleManager;
	
	private PagerModel<Role> pager;

	private Role role;

	private int roleId;

	@Override
	public String execute() throws Exception {
		
		pager = roleManager.searchRoles();
		
		return "index";
	}
	
	// 打开添加界面
	public String addInput() throws Exception {
		
		return "add_input";
	}
	
	public String add() throws Exception {
		roleManager.addRole(role);
		return "pub_add_success";
	}
	
	public String del() throws Exception {
		roleManager.delRole(roleId);
		return "pub_del_success";
	}
	
	// TODO getters and setters...
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
	public PagerModel<Role> getPager() {
		return pager;
	}
	public void setPager(PagerModel<Role> pager) {
		this.pager = pager;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
