package com.bjsxt.oa.manager;

import java.util.List;

public interface JbpmFacade {
	
	/**
	 * 部署流程定义
	 * @param processDef 流程定义文件的数据
	 * @return 流程名称
	 */
	public String deployProcessDefinition(byte[] processDef);
	
	/**
	 * 删除流程定义，根据流程名称，删除同一个名称的所有的ProcessDefinition对象
	 * @param processName 流程名称
	 */
	public void delProcessDefinition(String processName);
	
	/**
	 * 添加流程实例，在创建公文的同时需要调用这个方法
	 * 在这个方法中，根据流程名称得到流程定义对象，并据此创建流程实例，
	 * 然后把公文ID绑定到流程实例变量中
	 * @param processName 流程名称
	 * @param docId 公文ID
	 * @return 流程实例ID
	 */
	public long addProcessInstance(String processName,int docId);
	
	/**
	 * 删除公文的同时，需要删除流程实例
	 * @param processInstanceId 流程实例ID
	 */
	public void delProcessInstance(long processInstanceId);
	
	/**
	 * 搜索流转到某个参与者手上的公文列表
	 * @param actorId 参与者的标识（用户账号）
	 * @return List中的元素是docId
	 */
	public List searchMyTaskList(String actorId);
	
	/**
	 * 查询下一步Transition列表
	 * @param processInstanceId 流程实例ID
	 * @param actorId 参与者标识（用户账号）
	 * @return List的元素是Transition对象的名称
	 */
	public List searchNextTransitions(long processInstanceId,String actorId);
	
	/**
	 * 触发JBPM引擎流转到下一步
	 * @param processInstanceId 流程实例的ID
	 * @param actorId 参与者标识（用户账号）
	 * @param transitionName transition的名称
	 * @return 流转之后，当前流程实例对应的rootToken所指向的节点的名称，这个名称
	 *         将作为公文流转之后的状态
	 */
	public String nextStep(long processInstanceId,String actorId,String transitionName);
}
