package com.bjsxt.oa.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.UserManager;
import com.bjsxt.oa.model.Person;
import com.bjsxt.oa.model.Role;
import com.bjsxt.oa.model.User;
import com.bjsxt.oa.model.UsersRoles;
import com.bjsxt.oa.web.SystemException;

public class UserManagerImpl extends AbstractManager implements UserManager {

	@Override
	public void addUser(User user, int personId) {

		if (personId == 0) {
			throw new SystemException("必须选择相应的人员信息！");
		}
		user.setPerson(
				(Person)getHibernateTemplate().load(Person.class, personId)
				);
		
		// 设置创建时间
		user.setCreateTime(new Date());
		
		getHibernateTemplate().save(user);
	}

	@Override
	public void updateUser(User user, int personId) {
		if (personId == 0) {
			throw new SystemException("必须选择相应的人员信息！");
		}
		user.setPerson(
				(Person)getHibernateTemplate().load(Person.class, personId)
				);
		getHibernateTemplate().update(user);
	}

	@Override
	public void delUser(int userId) {
		getHibernateTemplate().delete(
				getHibernateTemplate().load(User.class, userId)
				);
	}

	@Override
	public User findUser(int userId) {
		return (User)getHibernateTemplate().load(User.class, userId);
	}

	@Override
	public PagerModel searchUsers() {
		return searchPaginated("from User");
	}

	@Override
	public void addOrUpdateUserRole(int userId, int roleId, int orderNo) {

		// 首先根据userId和roleId，查找是否存在相应的记录
		UsersRoles ur = findUsersRoles(userId, roleId);
		
		if (ur == null) {
			ur = new UsersRoles();
			ur.setOrderNo(orderNo);
			ur.setUser((User)getHibernateTemplate().load(User.class, userId));
			ur.setRole((Role)getHibernateTemplate().load(Role.class, roleId));
			getHibernateTemplate().save(ur);
			return;
		}
		
		ur.setOrderNo(orderNo);
		getHibernateTemplate().update(ur);
	}

	@Override
	public void delUserRole(int userId, int roleId) {

		getHibernateTemplate().delete(findUsersRoles(userId, roleId));
		
	}

	@Override
	public List searchUserRoles(int userId) {
		return getHibernateTemplate().find("select ur from UsersRoles ur where ur.user.id = ?", userId);
	}

	// 因为设置了User的auto-import="false"，所以在这里使用HQL查询的时候，必须使用全路径的类名
	@Override
	public User login(String username, String password) {
		User u = (User)getSession().createQuery(
				"select u from com.bjsxt.oa.model.User u where u.username = ?")
				.setParameter(0, username)
				.uniqueResult();
		
		if (u == null) {
			throw new SystemException("不存在该用户！");
		}
		
		if (!u.getPassword().equals(password)) {
			throw new SystemException("密码错误！");
		}
		
		if (u.getExpireTime() != null) {
			// 现在时间
			Calendar now = Calendar.getInstance();
			
			// 失效时间
			Calendar expireTime = Calendar.getInstance();
			expireTime.setTime(u.getExpireTime());
			
			// 比较现在时间是否在失效时间
			if (now.after(expireTime)) {
				throw new SystemException("该账户已失效！");
			}
		}
		
		return u;
	}

	@Override
	public List searchUsersOfRole(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

	private UsersRoles findUsersRoles(int userId, int roleId)	{
		return (UsersRoles)getSession().createQuery(
				"select ur from UsersRoles ur where ur.user.id = ? and ur.role.id = ?")
				.setParameter(0, userId)
				.setParameter(1, roleId)
				.uniqueResult();
	}
}
