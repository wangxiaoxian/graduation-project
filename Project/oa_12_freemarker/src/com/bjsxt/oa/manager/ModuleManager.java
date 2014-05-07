package com.bjsxt.oa.manager;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.model.Module;

public interface ModuleManager {
	public void addModule(Module module,int parentId);
	public void delModule(int moduleId);
	public void updateModule(Module module,int parentId);
	public Module findModule(int moduleId);
	public PagerModel searchModules(int parentId);
}
