package com.bjsxt.oa.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.DocumentManager;
import com.bjsxt.oa.manager.JbpmFacade;
import com.bjsxt.oa.model.ApproveInfo;
import com.bjsxt.oa.model.Document;
import com.bjsxt.oa.model.User;
import com.bjsxt.oa.model.Workflow;

public class DocumentManagerImpl extends AbstractManager implements
		DocumentManager {
	
	private JbpmFacade jbpmFacade;

	/**
	 * 添加公文
	 */
	@Override
	public void addDocument(Document document, int workflowId, int userId, Map props) {
		
		//保存公文信息
		document.setWorkflow((Workflow)getHibernateTemplate().load(Workflow.class, workflowId));
		document.setCreator((User)getHibernateTemplate().load(User.class, userId));
		document.setStatus(Document.STATUS_NEW);
		document.setCreateTime(new Date());
		
		//将未转换的动态属性，转换为我们需要的类型
		document.setPropertiesMap(props);
		
		getHibernateTemplate().save(document);
		
		//添加流程实例
		long processInstanceId = jbpmFacade.addProcessInstance(document.getWorkflow().getName(), document.getId());
		
		//绑定流程实例的标识到公文对象
		document.setProcessInstanceId(processInstanceId);
		
		//其实，不必调用update方法也可以，hibernate关闭session的时候会检查对象与数据库的数据是否一致，
		//没有的话会自动发出update语句，
		//这里是为了代码的可读性比较好才加入了update语句
		getHibernateTemplate().update(document);
	}

	/**
	 * 更新公文信息
	 */
	@Override
	public void updateDocument(Document document, int workflowId, int userId) {
		// TODO Auto-generated method stub

	}

	/**
	 * 查找指定公文
	 */
	@Override
	public Document findDocument(int documentId) {

		return (Document) getHibernateTemplate().load(Document.class, documentId);
	}

	/**
	 * 查找用户创建的所有公文
	 */
	@Override
	public PagerModel searchMyDocuments(int userId) {
		
		return searchPaginated("select d from Document d where d.creator.id = ?", userId);
	}

	/**
	 * 删除公文
	 */
	@Override
	public void delDocument(int documentId) {

		Document document = (Document) getHibernateTemplate().load(Document.class, documentId);
		
		// 删除公文信息
		getHibernateTemplate().delete(document);
		
		// 删除流程实例
		jbpmFacade.delProcessInstance(document.getProcessInstanceId());
	}

	/**
	 * 审批公文
	 */
	@Override
	public void addApproveInfo(ApproveInfo approveInfo, int documentId,
			int approverId) {

		approveInfo.setDocument((Document) getHibernateTemplate().load(Document.class, documentId));
		approveInfo.setApprover((User) getHibernateTemplate().load(User.class, documentId));
		getHibernateTemplate().save(approveInfo);
	}

	/**
	 * 查找用户已审批的公文
	 */
	@Override
	public PagerModel searchApprovedDocuments(int userId) {
		return searchPaginated("select distinct ai.document from ApproveInfo ai where ai.approver.id = ?", userId);
	}

	/**
	 * 查找公文的审批历史
	 */
	@Override
	public List searchApproveInfos(int documentId) {
		return getHibernateTemplate().find("from ApproveInfo ai where ai.document.id = ?", documentId);
	}

	/**
	 * 查找正在等待审批的公文
	 */
	@Override
	public List searchApprovingDocuments(int userId) {
		User user = (User) getHibernateTemplate().load(User.class, userId);
		
		//搜索已流转到用户那里的公文标识列表
		List docIds = jbpmFacade.searchMyTaskList(user.getUsername());
		if (docIds == null || docIds.isEmpty()) {
			return null;
		}
		
		//根据公文标识查找所有的公文对象
		return getSession()
				.createQuery("select d from Document d where d.id in (:ids)")
				.setParameterList("ids", docIds)
				.list();
	}

	@Override
	public List searchNextSteps(int documentId, int userId) {
		Document doc = findDocument(documentId);
		User user = (User)getHibernateTemplate().load(User.class, userId);
		String username = user.getUsername();
		
		return jbpmFacade.searchNextTransitions(doc.getProcessInstanceId(), username);
	}

	@Override
	public void submitToWorkflow(int userId, int documentId,
			String transitionName) {

		User user = (User) getHibernateTemplate().load(User.class, userId);
		Document document = (Document) getHibernateTemplate().load(Document.class, documentId);
		
		String status = jbpmFacade.nextStep(document.getProcessInstanceId(), user.getUsername(), transitionName);
		
		document.setStatus(status);
		getHibernateTemplate().update(document);
	}

	// getters and setters...
	public void setJbpmFacade(JbpmFacade jbpmFacade) {
		this.jbpmFacade = jbpmFacade;
	}


}
