package com.bjsxt.oa.web;

import java.util.List;

import com.bjsxt.oa.manager.AclManager;
import com.bjsxt.oa.model.Module;
import com.bjsxt.oa.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private AclManager aclManager;
	
	private List<Module> modules;
	
	// 打开outlook界面
	public String outlook() throws Exception {
		
		User user = (User)ActionContext.getContext().getSession().get("login");
		
		modules = aclManager.searchModules(user.getId());
		
		return "outlook";
	}
	
	// 打开main界面
	public String main() throws Exception {
		
		return "main";
	}
	
	// TODO getters and setters...
	public void setAclManager(AclManager aclManager) {
		this.aclManager = aclManager;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

}
