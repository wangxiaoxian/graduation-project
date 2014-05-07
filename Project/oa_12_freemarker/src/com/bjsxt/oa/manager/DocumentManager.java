package com.bjsxt.oa.manager;

import java.util.List;
import java.util.Map;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.model.ApproveInfo;
import com.bjsxt.oa.model.Document;

public interface DocumentManager {
	
	/**
	 * 添加公文信息
	 * @param document 公文对象
	 * @param workflowId 公文对应的流程ID
	 * @param userId 公文的创建者ID
	 */
	public void addDocument(Document document,int workflowId,int userId, Map props);
	
	/**
	 * 更新公文信息
	 * @param document
	 */
	public void updateDocument(Document document,int workflowId,int userId);
	
	/**
	 * 查找某个公文
	 * @param documentId
	 * @return
	 */
	public Document findDocument(int documentId);
	
	/**
	 * 搜索我的公文列表（即搜索由当前登录用户创建的公文列表）
	 * @param userId 当前登录用户
	 * @return
	 */
	public PagerModel searchMyDocuments(int userId);
	
	/**
	 * 删除公文信息
	 * @param documentId
	 */
	public void delDocument(int documentId);
	
	/**
	 * 审批公文，记录审批信息
	 * @param approveInfo 审批信息
	 * @param documentId 被审批的公文
	 * @param approverId 审批者，取当前登录用户的ID
	 */
	public void addApproveInfo(ApproveInfo approveInfo,int documentId,int approverId);
	
	/**
	 * 查询(当前登录用户的)已审公文列表
	 * @param userId 用户ID，取当前登录用户的ID
	 * @return
	 */
	public PagerModel searchApprovedDocuments(int userId);
	
	/**
	 * 查询公文的审批历史（即查询公文都经过了哪些人审批）
	 * @param documentId 公文的ID
	 * @return
	 */
	public List searchApproveInfos(int documentId);
	
	/**
	 * 查询待审（即等待当前登录用户审批的）公文列表
	 * @param userId 当前登录用户的ID
	 * @return
	 */
	public List searchApprovingDocuments(int userId);
	
	/**
	 * 查询下一个可选步骤列表（公文ID，用户标识）
	 * @param documentId
	 * @param userId
	 * @return
	 */
	public List searchNextSteps(int documentId,int userId);
	
	/**
	 * 提交到流程
	 * @param userId 当前登录用户的ID
	 * @param documentId 被提交的公文ID
	 * @param transistionName 要提交到哪里去
	 */
	public void submitToWorkflow(int userId,int documentId,String transistionName);
}
