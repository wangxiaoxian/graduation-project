package com.bjsxt.oa.web;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.PersonManager;
import com.bjsxt.oa.model.Person;
import com.opensymphony.xwork2.ActionSupport;

public class PersonAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private PersonManager personManager;
	
	private Person personForm;
	private int orgId;
	private PagerModel<Person> pager;
	private int personId;

	/**
	 * 人员管理的主页
	 */
	@Override
	public String execute() throws Exception {
		pager = personManager.searchPersons();
		return "index";
	}
	
	/**
	 * 添加界面
	 * @return
	 * @throws Exception
	 */
	public String addInput() throws Exception {
		
		return "add_input";
	}
	
	/**
	 * 添加功能
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		personManager.addPerson(personForm, orgId);
		return "pub_add_success";
	}
	
	/**
	 * 更新界面
	 * @return
	 * @throws Exception
	 */
	public String updateInput() throws Exception {
		personForm = personManager.findPerson(personId);
		return "update_input";
	}
	
	/**
	 * 更新功能
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		personManager.updatePerson(personForm, orgId);
		return "pub_update_success";
	}
	
	/**
	 * 删除功能
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		personManager.delPerson(personId);
		return "pub_del_success";
	}
	
	// TODO getters and setter...
	public Person getPersonForm() {
		return personForm;
	}
	public void setPersonForm(Person personForm) {
		this.personForm = personForm;
	}
	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public PagerModel<Person> getPager() {
		return pager;
	}
	public void setPager(PagerModel<Person> pager) {
		this.pager = pager;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
}
