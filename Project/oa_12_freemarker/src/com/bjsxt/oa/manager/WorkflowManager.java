package com.bjsxt.oa.manager;

import java.util.List;

import com.bjsxt.oa.model.Workflow;

public interface WorkflowManager {

	/**
	 * 
	 * @param processDef
	 * @param processImage
	 */
	public void addOrUpdateWorkflow(byte[] processDef,byte[] processImage);
	
	/**
	 * 删除流程定义
	 * @param workflowId
	 */
	public void delWorkflow(int workflowId);
	
	/**
	 * 查找特定流程定义信息
	 * @param workflowId
	 * @return
	 */
	public Workflow findWorkflow(int workflowId);
	
	/**
	 * 查找已有的流程定义信息
	 * @return
	 */
	public List<Workflow> searchAllWorkflows();
	
	
}
