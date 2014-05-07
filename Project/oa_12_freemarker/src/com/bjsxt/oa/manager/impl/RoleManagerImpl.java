package com.bjsxt.oa.manager.impl;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.RoleManager;
import com.bjsxt.oa.model.Role;

public class RoleManagerImpl extends AbstractManager implements RoleManager {

	@Override
	public void addRole(Role role) {

		getHibernateTemplate().save(role);
	}

	@Override
	public void delRole(int roleId) {
		
		getHibernateTemplate().delete(
				getHibernateTemplate().load(Role.class, roleId)
				);
	}

	@Override
	public void updateRole(Role role) {

		getHibernateTemplate().update(role);
	}

	@Override
	public Role findRole(int roleId) {

		return (Role)getHibernateTemplate().load(Role.class, roleId);
	}

	@Override
	public PagerModel searchRoles() {
		
		return searchPaginated("from Role");
	}

}
