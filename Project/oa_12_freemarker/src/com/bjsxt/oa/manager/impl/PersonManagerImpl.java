package com.bjsxt.oa.manager.impl;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.PersonManager;
import com.bjsxt.oa.model.Organization;
import com.bjsxt.oa.model.Person;
import com.bjsxt.oa.web.SystemException;

public class PersonManagerImpl extends AbstractManager implements PersonManager {

	@Override
	public void addPerson(Person person, int orgId) {

		if (orgId == 0) {
			throw new SystemException("机构不允许为空");
		}
		
		person.setOrg(
				(Organization)getHibernateTemplate().load(Organization.class, orgId)
		);
		
		getHibernateTemplate().save(person);
	}

	@Override
	public void delPerson(int personId) {
		
		getHibernateTemplate().delete(
				getHibernateTemplate().load(Person.class, personId)
		);
	}

	@Override
	public Person findPerson(int personId) {
		return (Person)getHibernateTemplate().load(Person.class, personId);
	}

	@Override
	public PagerModel searchPersons() {
		
		return searchPaginated("from Person");
	}
	
	@Override
	public PagerModel searchPersons(int orgId) {
		return searchPaginated("select p from Person p where p.org.id = " + orgId);
	}

	@Override
	public void updatePerson(Person person, int orgId) {
		if(orgId != 0){
			person.setOrg((Organization)this.getHibernateTemplate().load(Organization.class, orgId));
		}
		getHibernateTemplate().update(person);
	}
}
