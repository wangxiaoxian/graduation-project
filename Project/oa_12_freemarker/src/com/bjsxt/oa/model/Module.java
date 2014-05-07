package com.bjsxt.oa.model;

import java.util.Set;


/**
 * 
 * @author Administrator
 * @hibernate.class table="T_Module"
 */
public class Module {
	
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property
	 */
	private String name;
	
	/**
	 * @hibernate.property
	 */
	private String url;
	
	/**
	 * @hibernate.property unique="true"
	 */
	private String sn;
	
	/**
	 * @hibernate.property
	 */
	private int orderNo;
	
	/**
	 * @hibernate.many-to-one column="pid"
	 */
	private Module parent;
	
	/**
	 * @hibernate.set lazy="extra" inverse="true"
	 * @hibernate.key column="pid"
	 * @hibernate.one-to-many class="com.bjsxt.oa.model.Module"
	 */
	private Set children;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public Module getParent() {
		return parent;
	}
	public void setParent(Module parent) {
		this.parent = parent;
	}
	public Set getChildren() {
		return children;
	}
	public void setChildren(Set children) {
		this.children = children;
	}
}
