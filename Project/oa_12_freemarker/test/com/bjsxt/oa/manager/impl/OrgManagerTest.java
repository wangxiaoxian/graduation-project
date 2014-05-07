package com.bjsxt.oa.manager.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractTransactionalSpringContextTests;

import com.bjsxt.oa.manager.OrgManager;
import com.bjsxt.oa.model.Organization;

import junit.framework.TestCase;

public class OrgManagerTest extends AbstractTransactionalSpringContextTests {

	private OrgManager orgManager;
	
	@Override
	protected String[] getConfigLocations() {
		return new String[]{"applicationContext-*.xml"};
	}

	public void testAddOrg() {
		for(int i=0; i<10; i++){
			Organization p = new Organization();
			p.setName("Org"+i);
			orgManager.addOrg(p, 0);
			for(int j=0; j<5; j++){
				Organization s = new Organization();
				s.setName(p.getName()+"'s child "+j);
				orgManager.addOrg(s, p.getId());
			}
		}
	}
	
	public void testAddOrg1(){
		Organization p = new Organization();
		p.setName("���Ի�");
		orgManager.addOrg(p, 0);
		
		//����Ҫ�ع�
		setComplete();
	}

	public void testDelOrg() {
		orgManager.delOrg(7);
	}

	public void testUpdateOrg() {
		fail("Not yet implemented");
	}

	public void testFindOrg() {
		try {
			Organization org = orgManager.findOrg(3);
			System.out.println(org.getName());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

//	public void testSearchOrgs() {
//		List orgs = orgManager.searchOrgs(0);
//		for (Iterator iterator = orgs.iterator(); iterator.hasNext();) {
//			Organization org = (Organization) iterator.next();
//			System.out.println(org.getName());
//		}
//	}

	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}

}
