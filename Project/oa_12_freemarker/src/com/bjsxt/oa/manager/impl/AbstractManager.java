package com.bjsxt.oa.manager.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.SystemContext;
import com.bjsxt.oa.model.Organization;
import com.bjsxt.oa.web.SystemException;

public class AbstractManager extends HibernateDaoSupport {
	
	// 以下的三个方法，分页的参数都的通过ThreadLocal传递进来
	public PagerModel<Organization> searchPaginated(String hql) {
		return searchPaginated(hql, null, SystemContext.getOffset(), SystemContext.getPagesize());
	}
	public PagerModel<Organization> searchPaginated(String hql, Object obj) {
		return searchPaginated(hql, new Object[]{obj}, SystemContext.getOffset(), SystemContext.getPagesize());
	}
	public PagerModel<Organization> searchPaginated(String hql, Object[] params) {
		return searchPaginated(hql, params, SystemContext.getOffset(), SystemContext.getPagesize());
	}
	
	// 以下的三个方法，分页的参数通过action传递进来
	public PagerModel<Organization> searchPaginated(String hql, int offset, int pagesize) {
		return searchPaginated(hql, null, offset, pagesize);
	}
	public PagerModel<Organization> searchPaginated(String hql, Object obj, int offset, int pagesize) {
		return searchPaginated(hql, new Object[]{obj}, offset, pagesize);
	}
	
	/**
	 * 根据HQL语句进行分页查询
	 * @param hql HQL语句
	 * @param params HQL带的多个参数
	 * @param offset 从第几条开始查询
	 * @param pagesize 每页显示多少条
	 * @return 
	 */
	public PagerModel<Organization> searchPaginated(String hql, Object[] params, int offset, int pagesize) {
		// 获取记录总数
		String countHql = getCountQuery(hql);
		Query query = getSession().createQuery(countHql);
		if (params != null && params.length != 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		int total = ((Long)query.uniqueResult()).intValue();
		
		// 获取结果集
		query = getSession().createQuery(hql);
		if (params != null && params.length != 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		List<Organization> datas = query.setFirstResult(offset)
					 				  .setMaxResults(pagesize)
					 				  .list();
		
		PagerModel<Organization> pm = new PagerModel<Organization>();
		pm.setDatas(datas);
		pm.setTotal(total);
		return pm;
	}
	
	/**
	 * 将原始的查询结果集的hql转化为查询记录数的hql
	 * @param hql
	 * @return
	 */
	public String getCountQuery(String hql) {
		int index = hql.indexOf("from");
		if (index != -1) {
			return "select count(*) " + hql.substring(index);
		}
		throw new SystemException("无效的HQL查询语句");
	}
}
