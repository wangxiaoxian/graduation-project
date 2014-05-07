package com.bjsxt.oa.model;

/**
 * 表单的类型
 * @author Administrator
 *
 */
public class FieldType {

	private int id;
	
	/**
	 * 类型名称，如：字符串，整形，文件等等
	 */
	private String name;
	
	/**
	 * 对应的java类型，如：String, Integer, File
	 */
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
