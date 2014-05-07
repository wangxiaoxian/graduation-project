package com.bjsxt.oa.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.OrgManager;
import com.bjsxt.oa.model.Organization;
import com.bjsxt.oa.web.SystemException;

//@Component("orgManager")
public class OrgManagerImpl extends AbstractManager implements OrgManager {
	//private HibernateTemplate hibernateTemplate;
	private Logger logger = Logger.getLogger(OrgManagerImpl.class);
	
	@Override
	public void addOrg(Organization org, int parentId) {
		if (parentId != 0) {
			org.setParent(
				(Organization)getHibernateTemplate().load(Organization.class, parentId)
			);
		}
		getHibernateTemplate().save(org);
		
		// 机构的sn取名方式：若parent为空，则直接为id；若不为空，则取parent的sn+"_"+自己的id
		org.setSn(
			org.getParent() == null ? 
				("" + org.getId()) : 
					(org.getParent().getSn() + "_" + org.getId()) 
		);
		getHibernateTemplate().update(org);
	}
	
	@Override
	public void addOrg(Organization org) {
		getHibernateTemplate().save(org);
		
	}

	@Override
	public void delOrg(int orgId) {
		Organization org = (Organization)getHibernateTemplate().load(Organization.class, orgId);
		if (org.getChildren() != null && org.getChildren().size() != 0) {
			logger.debug("出现异常，执行了删除有子机构的操作");
			throw new SystemException("该机构有子机构，不能删除", "01010001");
		}
		getHibernateTemplate().delete(org);
		getHibernateTemplate().flush();
	}

	@Override
	public Organization findOrg(int orgId) {
		if (orgId == 0) {
			return (Organization)getHibernateTemplate().load(Organization.class, null);
		}
		return (Organization)getHibernateTemplate().load(Organization.class, orgId);
	}

	@Override
	public void udpateOrg(Organization org, int parentId) {
		if (parentId != 0) {
			org.setParent(
				(Organization)getHibernateTemplate().load(Organization.class, parentId)
			);
		}
		
		getHibernateTemplate().update(org);
	}

	// 分页的参数通过ThreadLocal直接传递给AbstractManager,因此，这里不需要指定分页的参数
	@Override
	public PagerModel<Organization> findOrgs(int parentId) {
		
		if(parentId == 0) {
			return searchPaginated("select o from Organization o where o.parent.id=null");
		}
		return searchPaginated("select o from Organization o where o.parent.id=?", parentId);
	}

}
