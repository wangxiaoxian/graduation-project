package com.bjsxt.oa.model;

import java.util.Date;

/**
 * 审批历史信息
 */
public class ApproveInfo {

	private int id;
	
	/**
	 * 审批意见
	 */
	private String comment;
	
	/**
	 * 审批时间
	 */
	private Date approveTime;
	
	/**
	 * 审批的公文
	 */
	private Document document;
	
	/**
	 * 审批者
	 */
	private User approver;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public User getApprover() {
		return approver;
	}
	public void setApprover(User approver) {
		this.approver = approver;
	}
}
