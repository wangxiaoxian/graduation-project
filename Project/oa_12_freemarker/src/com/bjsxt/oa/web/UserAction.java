package com.bjsxt.oa.web;

import java.util.List;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.PersonManager;
import com.bjsxt.oa.manager.RoleManager;
import com.bjsxt.oa.manager.UserManager;
import com.bjsxt.oa.model.Person;
import com.bjsxt.oa.model.Role;
import com.bjsxt.oa.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private PersonManager personManager;
	private UserManager userManager;
	private RoleManager roleManager;
	
	private PagerModel<Person> pager;
	private User user;
	private int personId;
	private int userId;
	private int roleId;
	// 优先级
	private int orderNo;
	// 某个用户的所拥有的角色
	private List<Role> urs;
	
	/**
	 * 首页，显示人员列表
	 */
	@Override
	public String execute() throws Exception {
		
		pager = personManager.searchPersons();
		
		return "index";
	}
	
	// 打开添加界面
	public String addInput() throws Exception {
		
		return "add_input";
	}
	
	public String add() throws Exception {
		userManager.addUser(user, personId);
		return "pub_add_success";
	}
	
	public String updateInput() throws Exception {
		user = userManager.findUser(userId);
		return "update_input";
	}
	
	public String update() throws Exception {
		userManager.updateUser(user, personId);
		
		return "pub_update_success";
	}
	
	//删除分配给用户的角色，页面上需要传输过来的参数包括：用户标识、角色标识
	public String delUserRole() throws Exception {
		
		userManager.delUserRole(userId, roleId);
		
		return "pub_add_success";
	}
	
	public String del() throws Exception {
		userManager.delUser(userId);
		return "pub_del_success";
	}
	
	//打开用户已有角色的列表页面，在页面上需要显示：用户的姓名、以及用户已拥有的角色列表
	public String userRoleList() throws Exception {
		//用户信息
		user = userManager.findUser(userId);
		
		//需要加载已分配给用户的角色列表
		urs = userManager.searchUserRoles(userId);
		
		return "user_role_list";
	}
	
	//打开给用户分配角色页面：即从角色列表中选择某个角色，并设置优先级
	public String userRoleInput() throws Exception {
		//查找角色列表，并传输到界面，以便用户的选择
		pager = roleManager.searchRoles();
		
		return "user_role_input";
	}
	
	//给用户分配角色：页面上需要传递过来的参数包括：用户标识、角色标识、优先级
	public String addUserRole() throws Exception {
		userManager.addOrUpdateUserRole(userId, roleId, orderNo);
		
		return "pub_add_success";
	}

	// TODO getters and setters...
	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}
	public PagerModel<Person> getPager() {
		return pager;
	}
	public void setPager(PagerModel<Person> pager) {
		this.pager = pager;
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
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Role> getUrs() {
		return urs;
	}
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
