package com.bjsxt.oa.web;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.ModuleManager;
import com.bjsxt.oa.model.Module;
import com.opensymphony.xwork2.ActionSupport;

public class ModuleAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private ModuleManager moduleManager;
	
	private Module module;
	
	private PagerModel<Module> pager;
	
	private int parentId = 0;
	
	private int moduleId;
	
	@Override
	public String execute() throws Exception {
		
		pager = moduleManager.searchModules(parentId);
		
		return "index";
	}
	
	public String addInput() throws Exception {
		
		return "add_input";
	}
	
	public String add() throws Exception {
		moduleManager.addModule(module, parentId);
		return "pub_add_success";
	}
	
	public String del() throws Exception {
		moduleManager.delModule(moduleId);
		return "pub_del_success";
	}
	
	// TODO getters and setters
	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public PagerModel<Module> getPager() {
		return pager;
	}
	public void setPager(PagerModel<Module> pager) {
		this.pager = pager;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
}
