package com.bjsxt.oa.manager.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bjsxt.oa.manager.JbpmFacade;
import com.bjsxt.oa.manager.WorkflowManager;
import com.bjsxt.oa.model.Workflow;

public class WorkflowManagerImpl extends HibernateDaoSupport implements WorkflowManager {

	private JbpmFacade jbpmFacade;
	
	@Override
	public void addOrUpdateWorkflow(byte[] processDef, byte[] processImage) {

		String workflowName = jbpmFacade.deployProcessDefinition(processDef);
		
		//首先根据流程名称，查询是否已有Workflow对象
		Workflow wf = (Workflow)getSession().createQuery("select w from Workflow w where w.name = ?")
			.setParameter(0, workflowName)
			.uniqueResult();
		if(wf == null){
			wf = new Workflow();
			wf.setName(workflowName);
			wf.setProcessDef(processDef);
			wf.setProcessImage(processImage);
			getHibernateTemplate().save(wf);
		}else{
			wf.setProcessDef(processDef);
			wf.setProcessImage(processImage);
			getHibernateTemplate().update(wf);
		}
	}

	@Override
	public void delWorkflow(int workflowId) {
		
		Workflow wf = findWorkflow(workflowId);
		jbpmFacade.delProcessDefinition(wf.getName());
		getHibernateTemplate().delete(wf);
	}

	@Override
	public Workflow findWorkflow(int workflowId) {
		
		return (Workflow)getHibernateTemplate().load(Workflow.class, workflowId);
	}

	@Override
	public List<Workflow> searchAllWorkflows() {
		
		return getHibernateTemplate().find("from Workflow");
	}
	
	/**
	 * 获取JbpmContext对象，需要将JbpmContext的session设置为当前的session对象
	 * @return
	 */
	
	public void setJbpmFacade(JbpmFacade jbpmFacade) {
		this.jbpmFacade = jbpmFacade;
	}
}
