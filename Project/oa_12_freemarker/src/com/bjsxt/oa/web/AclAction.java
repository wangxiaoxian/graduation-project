package com.bjsxt.oa.web;

import java.util.ArrayList;
import java.util.List;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.SystemContext;
import com.bjsxt.oa.manager.ModuleManager;
import com.bjsxt.oa.manager.RoleManager;
import com.bjsxt.oa.manager.UserManager;
import com.bjsxt.oa.model.ACL;
import com.bjsxt.oa.model.Module;
import com.bjsxt.oa.model.Role;
import com.bjsxt.oa.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class AclAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String principalType;
	private int principalId;
	private ACL acl = new ACL();
	
	private RoleManager roleManager;
	private Role role = new Role();
	
	private UserManager userManager;
	private User user = new User();
	
	private ModuleManager moduleManager;
	private List<Module> modules = new ArrayList<Module>();

	// 打开ACL授权界面
	// 接收的参数：pricipalType, pricipalSn
	// 输出的参数：模块列表，角色或用户对象
	@Override
	public String execute() throws Exception {
		
		// 如果主体类型是角色
		if (ACL.TYPE_ROLE.equals(principalType)) {
			role = roleManager.findRole(principalId);
		} else if (ACL.TYPE_USER.equals(principalType)) {
			user = userManager.findUser(principalId);
		} else {
			throw new SystemException("无效的主体类型！！！");
		}
		
		// 这里不需要分页，所以把PagerModel中的数据都取出来
		SystemContext.setOffset(0);
		SystemContext.setPagesize(Integer.MAX_VALUE);
		PagerModel pm = moduleManager.searchModules(0);
		modules = pm.getDatas();
		
		return "index";
	}

	// TODO getters and setters...
	public ACL getAcl() {
		return acl;
	}
	public void setAcl(ACL acl) {
		this.acl = acl;
	}
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public String getPrincipalType() {
		return principalType;
	}
	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}
	public int getPrincipalId() {
		return principalId;
	}
	public void setPrincipalId(int principalId) {
		this.principalId = principalId;
	}
}
