package com.bjsxt.oa.manager.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

import com.bjsxt.oa.manager.JbpmFacade;

public class JbpmFacadeImpl extends AbstractManager implements JbpmFacade {

	private JbpmConfiguration jbpmConfiguration;
	
	@Override
	public long addProcessInstance(String processName, int docId) {
		
		JbpmContext context = getJbpmContext();
		
		ProcessDefinition def = context.getGraphSession().findLatestProcessDefinition(processName);
		
		ProcessInstance instance = new ProcessInstance(def);
		
		instance.getContextInstance().setVariable("document", docId);
		
		context.save(instance);
		
		return instance.getId();
	}

	@Override
	public void delProcessDefinition(String processName) {
		JbpmContext context = getJbpmContext();
		List defs = context.getGraphSession().findAllProcessDefinitionVersions(processName);
		for (Iterator iterator = defs.iterator(); iterator.hasNext();) {
			ProcessDefinition def = (ProcessDefinition) iterator.next();
			context.getGraphSession().deleteProcessDefinition(def);
		}
	}

	@Override
	public void delProcessInstance(long processInstanceId) {
		JbpmContext context = getJbpmContext();
		context.getGraphSession().deleteProcessInstance(processInstanceId);
	}

	@Override
	public String deployProcessDefinition(byte[] processDef) {
		
		JbpmContext context = getJbpmContext();
		
		ProcessDefinition def = ProcessDefinition.parseXmlInputStream(
			new ByteArrayInputStream(processDef)
		);
		
		context.deployProcessDefinition(def);
		
		return def.getName();
	}

	@Override
	public String nextStep(long processInstanceId, String actorId,
			String transitionName) {
		
		JbpmContext context = getJbpmContext();
		ProcessInstance instance = context.getProcessInstance(processInstanceId);
		
		//当前节点
		String currentNodeName = instance.getRootToken().getNode().getName();
		
		//起点的名称
		String startNodeName = instance.getProcessDefinition().getStartState().getName();
				
		//如果是在起点
		if(startNodeName.equals(currentNodeName)){
			if(transitionName == null){
				instance.signal();
			}else{
				instance.signal(transitionName);
			}
		}else{
			List taskInstances = context.getTaskMgmtSession().findTaskInstances(actorId);
			for (Iterator iterator = taskInstances.iterator(); iterator
					.hasNext();) {
				TaskInstance ti = (TaskInstance) iterator.next();
				if(ti.getProcessInstance().getId() == processInstanceId){
					if(transitionName == null){
						ti.end();
					}else{
						ti.end(transitionName);
					}
					break;
				}
			}
		}
		
		//返回转向之后的节点名称
		return instance.getRootToken().getNode().getName();
	}

	@Override
	public List searchMyTaskList(String actorId) {
		
		JbpmContext context = getJbpmContext();
		List docIds = new ArrayList();
		List taskInstances = context.getTaskMgmtSession().findTaskInstances(actorId);
		for (Iterator iterator = taskInstances.iterator(); iterator.hasNext();) {
			TaskInstance ti = (TaskInstance) iterator.next();
			Integer docId = (Integer)ti.getProcessInstance().getContextInstance().getVariable("document");
			docIds.add(docId);
		}
		
		return docIds;
	}

	@Override
	public List searchNextTransitions(long processInstanceId, String actorId) {
		JbpmContext context = getJbpmContext();
		ProcessInstance instance = context.getProcessInstance(processInstanceId);
		
		//当前节点
		String currentNodeName = instance.getRootToken().getNode().getName();
		
		//起点的名称
		String startNodeName = instance.getProcessDefinition().getStartState().getName();
		
		Collection transitions = null;
		
		//如果是在起点
		if(startNodeName.equals(currentNodeName)){
			transitions = instance.getRootToken().getAvailableTransitions();
		}else{
			List taskInstances = context.getTaskMgmtSession().findTaskInstances(actorId);
			for (Iterator iterator = taskInstances.iterator(); iterator
					.hasNext();) {
				TaskInstance ti = (TaskInstance) iterator.next();
				if(ti.getProcessInstance().getId() == processInstanceId){
					transitions = ti.getAvailableTransitions();
					break;
				}
			}
		}
		
		List transitionNames = new ArrayList();
		
		//为了不把Transition对象直接暴露给OA系统，需要将其转换为名称列表
		for (Iterator iterator = transitions.iterator(); iterator.hasNext();) {
			Transition transition = (Transition) iterator.next();
			transitionNames.add(transition.getName());
		}
		
		return transitionNames;
	}
	
	private JbpmContext getJbpmContext(){
		JbpmContext context = jbpmConfiguration.createJbpmContext();
		context.setSession(getSession());
		return context;
	}

	public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration) {
		this.jbpmConfiguration = jbpmConfiguration;
	}

}
