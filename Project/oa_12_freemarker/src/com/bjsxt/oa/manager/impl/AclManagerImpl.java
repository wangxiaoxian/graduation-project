package com.bjsxt.oa.manager.impl;

import java.security.acl.Acl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bjsxt.oa.manager.AclManager;
import com.bjsxt.oa.manager.Permission;
import com.bjsxt.oa.model.ACL;
import com.bjsxt.oa.model.Module;

public class AclManagerImpl extends AbstractManager implements AclManager {

	/* (non-Javadoc)
	 * @see com.bjsxt.oa.manager.AclManager#addOrUpdatePermission(java.lang.String, int, int, int, boolean)
	 */
	@Override
	public void addOrUpdatePermission(String principalType, int principalId,
			int moduleId, int permission, boolean yes) {
		
		ACL acl = findACL(principalType, principalId, moduleId);

		// 如果存在acl实例，则更新其授权
		if (acl != null) {
			acl.setPermission(permission, yes);
			getHibernateTemplate().update(acl);
			return;
		}
		
		// 不存在acl，则创建ACL实例
		acl = new ACL();
		acl.setPrincipalType(principalType);
		acl.setPrincipalId(principalId);
		acl.setModuleId(moduleId);
		acl.setPermission(permission, yes);
		getHibernateTemplate().save(acl);
		
	}

	/**
	 * 删除某个用户、角色对于某个资源的所有权限信息
	 */
	@Override
	public void delPermission(String principalType, int principalId,
			int moduleId) {
		
		getHibernateTemplate().delete(
				findACL(principalType, principalId, moduleId)
				);
		
	}

	/**
	 * 设置某个用户对于某个资源的继承特性
	 */
	@Override
	public void addOrUpdateUserExtends(int userId, int moduleId, boolean yes) {

		ACL acl = findACL(ACL.TYPE_USER, userId, moduleId);
		
		// 如果存在acl实例，则更新其信息
		if (acl != null) {
			acl.setExtends(yes);
			getHibernateTemplate().update(acl);
			return;
		}
		
		// 不存在acl，则创建ACL实例
		acl = new ACL();
		acl.setPrincipalType(ACL.TYPE_USER);
		acl.setPrincipalId(userId);
		acl.setModuleId(moduleId);
		acl.setExtends(yes);
		getHibernateTemplate().save(acl);
	}

	/**
	 * 即时认证
	 */
	@Override
	public boolean hasPermission(int userId, int moduleId, int permission) {
		// 查找直接授予用户的权限
		ACL acl = findACL(ACL.TYPE_USER, userId, moduleId);
		
		// 找到该用户的授权
		if (acl != null) {
			int yesOrNo = acl.getPermission(permission);
			
			// 如果是确定的授权
			if (yesOrNo != ACL.ACL_NEUTRAL) {
				return yesOrNo == ACL.ACL_YES ? true : false;
			}
		}
		
		// 找不到用户的直接授权，或者是不确定的授权，则查找该用户的角色列表；按优先级从高到低
		String hql = "select r.id from UsersRoles ur join ur.role r join ur.user u "
				+ "where u.id = ? order by ur.orderNo";
		List<Integer> aclIds = getHibernateTemplate().find(hql, userId);
		
		// 依照角色优先级依次查找其授权
		for (Iterator<Integer> iterator = aclIds.iterator(); iterator.hasNext();) {
			Integer rid = iterator.next();
			acl = findACL(ACL.TYPE_ROLE, rid, moduleId);
			
			// 一旦发现授权，就不再继续往下找了；而且角色的是按优先级排序的
			if (acl != null) {
				return acl.getPermission(permission) == ACL.ACL_YES ? true : false;
			}
		}
		return false;
	}

	/**
	 * 查询用户拥有读取权限的模块列表（在登录后台管理主界面的时候，需要根据这个列表来生成导航菜单）
	 */
	@Override
	public List<Module> searchModules(int userId) {
		
		//查找用户拥有的角色，并按优先级从低到高排序
		String hql = "select r.id from UsersRoles ur join ur.role r join ur.user u " +
			"where u.id = ? order by ur.orderNo desc";
		List roleIds = getHibernateTemplate().find(hql,userId);
		
		Map temp = new HashMap();
		
		//依次查找角色的授权（ACL对象）
		for (Iterator iterator = roleIds.iterator(); iterator.hasNext();) {
			Integer rid = (Integer) iterator.next();
			List acls = findRoleAcls(rid);
			for (Iterator iterator2 = acls.iterator(); iterator2.hasNext();) {
				ACL acl = (ACL) iterator2.next();
				temp.put(acl.getModuleId(), acl);
			}
		}
		
		//针对用户查找有效的用户授权
		List acls = findUserAcls(userId);
		for (Iterator iterator = acls.iterator(); iterator.hasNext();) {
			ACL acl = (ACL) iterator.next();
			temp.put(acl.getModuleId(), acl);
		}
		
		//去除掉那些没有读取权限的授权记录
		Set entries = temp.entrySet();
		for (Iterator iterator = entries.iterator(); iterator.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			ACL acl = (ACL)entry.getValue();
			if(acl.getPermission(Permission.READ) != ACL.ACL_YES){
				iterator.remove();
			}
		}
		
		if(temp.isEmpty()){
			return new ArrayList();
		}
		
		String searchModules = "select m from Module m where m.id in (:ids)";
		
		return getSession()
			.createQuery(searchModules)
			.setParameterList("ids", temp.keySet())
			.list();
	}
	
	/**
	 * 根据主题类型，主题标识，资源标识查找acl实例
	 * @param principalType 主题类型
	 * @param principalId 主题标识
	 * @param moduleId 资源标识
	 * @return 查找到的acl实例，没有则返回null
	 */
	private ACL findACL(String principalType, int principalId,
			int moduleId) {
		ACL acl = (ACL)getSession().createQuery("select acl from ACL acl where acl.principalType = ? "
				+ "and acl.principalId = ?"
				+ "and acl.moduleId = ?")
				.setParameter(0, principalType)
				.setParameter(1, principalId)
				.setParameter(2, moduleId)
				.uniqueResult();
		return acl;
	}

	/**
	 * 根据角色查找角色的授权列表
	 * @param roleId 角色id
	 */ 
	private List<ACL> findRoleAcls(int roleId) {
		String hql = "select acl from ACL acl where acl.principalType = ? "
				+ "and acl.principalId = ?"; 
		return getHibernateTemplate().find(hql, new Object[]{ACL.TYPE_ROLE, roleId});
	}
	
	/**
	 * 根据用户查找直接授予用户的权限列表，（注意：如果授予用户的权限列表是继承的，则不应该包含在这个列表里面）
	 * @param userId
	 * @return
	 */
	private List<ACL> findUserAcls(int userId) {
		String hql = "select acl from ACL acl where acl.principalType = ? "
				+ "and acl.principalId = ? and acl.aclTriState = 0"; 
		return getHibernateTemplate().find(hql, new Object[]{ACL.TYPE_USER, userId});
	}

	@Override
	public List searchAclRecord(String principalType, int principalId) {
		String sql = "select moduleId, aclState&1, "
				+ "aclState&2, aclState&4, "
				+ "aclState&8, aclTriState "
				+ "from t_acl where principalType = '" 
				+ principalType + "' and principalId = " + principalId;
		return getSession().createSQLQuery(sql).list();
	}

	@Override
	public boolean hasPermissionByResourceSn(int userId, String resourceSn,
			int permission) {
		String hql = "select m.id from Module m where m.sn = ?"; 
		
		return hasPermission(
				userId, 
				(Integer)getSession().createQuery(hql).setParameter(0, resourceSn).uniqueResult(), 
				permission
				);
	}
}
