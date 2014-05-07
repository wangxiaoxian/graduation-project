package com.bjsxt.oa.web;

import com.bjsxt.oa.manager.UserManager;
import com.bjsxt.oa.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private UserManager userManager;
	
	private String username;
	private String password;

	@Override
	public String execute() throws Exception {
		
		User user = userManager.login(username, password);
		
		ActionContext.getContext().getSession().put("login", user);
		
		return "back_index";
	}

	// TODO getters and setters...
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
