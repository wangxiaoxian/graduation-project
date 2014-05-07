package com.bjsxt.oa.model;

import java.util.Date;

/**
 * 
 * @author Administrator
 * @hibernate.class table="T_User"
 */
public class User {
	
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property not-null="true" unique="true"
	 */
	private String username;
	
	/**
	 * @hibernate.property not-null="true"
	 */
	private String password;
	
	/**
	 * @hibernate.property
	 */
	private Date expireTime;
	
	/**
	 * @hibernate.property
	 */
	private Date createTime;
	
	/**
	 * @hibernate.many-to-one unique="true"
	 */
	private Person person;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
