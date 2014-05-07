package com.bjsxt.oa.manager;

import java.util.List;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.model.User;

public interface UserManager {
	public void addUser(User user,int personId);
	public void updateUser(User user,int personId);
	public void delUser(int userId);
	public User findUser(int userId);
	public PagerModel searchUsers();
	
	/**
	 * 在用户与角色之间建立关联
	 * @param userId
	 * @param roleId
	 * @param orderNo
	 */
	public void addOrUpdateUserRole(int userId,int roleId,int orderNo);
	
	/**
	 * 删除用户与角色之间的关联
	 * @param userId
	 * @param roleId
	 */
	public void delUserRole(int userId,int roleId);
	
	/**
	 * 搜索某个用户所拥有的角色列表
	 * @param userId
	 * @return 元素是UsersRoles对象
	 */
	public List searchUserRoles(int userId);
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username,String password);
	
	/**
	 * 搜索某个角色下的用户名列表
	 * @param roleName 角色名
	 * @return 用户名(username)列表
	 */
	public List searchUsersOfRole(String roleName);
}
