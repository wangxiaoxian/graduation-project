package com.bjsxt.oa.manager;

import java.util.List;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.model.Organization;

public interface OrgManager {
	/**
	 * 	
	 * @param org
	 * @param parentId
	 */
	public void addOrg(Organization org, int parentId);
	
	public void addOrg(Organization org);
	
	public void delOrg(int orgId);
	
	public void udpateOrg(Organization org, int parentId);
	
	public Organization findOrg(int orgId);
	
	public PagerModel<Organization> findOrgs(int parentId);
}
