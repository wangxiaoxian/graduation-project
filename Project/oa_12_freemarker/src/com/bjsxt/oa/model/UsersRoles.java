package com.bjsxt.oa.model;

/**
 * 
 * @author Administrator
 * @hibernate.class table="T_UsersRoles"
 */
public class UsersRoles {
	
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.many-to-one
	 */
	private Role role;
	
	/**
	 * @hibernate.many-to-one
	 */
	private User user;
	
	/**
	 * @hibernate.property
	 */
	private int orderNo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
}
