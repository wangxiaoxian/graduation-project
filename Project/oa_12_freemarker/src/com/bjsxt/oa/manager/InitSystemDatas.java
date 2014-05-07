package com.bjsxt.oa.manager;

/**
 * 初始化系统数据
 * @author Lee
 *
 */
public interface InitSystemDatas {
	
	/**
	 * 添加或更新（如果已存在则更新）系统的初始化数据
	 * @param xmlFilePath
	 */
	public void addOrUpdateInitDatas(String xmlFilePath);
}
