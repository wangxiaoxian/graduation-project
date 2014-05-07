package com.bjsxt.oa.manager.impl;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.ModuleManager;
import com.bjsxt.oa.model.Module;
import com.bjsxt.oa.web.SystemException;

public class ModuleManagerImpl extends AbstractManager implements ModuleManager {

	@Override
	public void addModule(Module module, int parentId) {

		if (parentId != 0) {
			module.setParent(
					(Module)getHibernateTemplate().load(Module.class, parentId)
					);
		}
		getHibernateTemplate().save(module);
	}

	@Override
	public void delModule(int moduleId) {
		Module module = (Module)getHibernateTemplate().load(Module.class, moduleId);
		if (module.getChildren().size() > 0) {
			throw new SystemException("存在子机构，不允许删除!");
		}

		getHibernateTemplate().delete(module);
	}

	@Override
	public void updateModule(Module module, int parentId) {

		if (parentId != 0) {
			module.setParent(
					(Module)getHibernateTemplate().load(Module.class, parentId)
					);
		}
		getHibernateTemplate().update(module);
	}

	@Override
	public Module findModule(int moduleId) {
		
		return (Module)getHibernateTemplate().load(Module.class, moduleId);
	}

	@Override
	public PagerModel searchModules(int parentId) {
		
		return searchPaginated("select m from Module m where "
				+ (parentId == 0 ? "m.parent is null" : "m.parent.id = " + parentId));
	}

}
