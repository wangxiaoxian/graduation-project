package com.bjsxt.oa.manager.impl;

import org.junit.Test;

import com.bjsxt.oa.manager.InitSystemDatas;

public class InitSystemDatasTest extends BaseUnitTest {
	private InitSystemDatas initSystemDatas;
	
	@Test
	public void testAddOrUpdateInitDatas() {
		initSystemDatas.addOrUpdateInitDatas("init_datas.xml");
		
		// 如果没有添加setComplete()方法spring就会保证数据不会持久化到数据库，不会对数据库造成影响
		setComplete();
	}
	
	public void setInitSystemDatas(InitSystemDatas initSystemDatas) {
		this.initSystemDatas = initSystemDatas;
	}

}
