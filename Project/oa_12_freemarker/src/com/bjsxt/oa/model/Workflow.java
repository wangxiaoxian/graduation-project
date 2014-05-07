package com.bjsxt.oa.model;

/**
 * 流程
 * @author Administrator
 *
 */
public class Workflow {

	private int id;
	
	/**
	 * 流程名称
	 */
	private String name;
	
	/**
	 * 流程定义文件
	 */
	private byte[] processDef;
	
	/**
	 * 流程定义图片
	 */
	private byte[] processImage;
	
	private FlowForm flowForm;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getProcessDef() {
		return processDef;
	}
	public void setProcessDef(byte[] processDef) {
		this.processDef = processDef;
	}
	public byte[] getProcessImage() {
		return processImage;
	}
	public void setProcessImage(byte[] processImage) {
		this.processImage = processImage;
	}
	public FlowForm getFlowForm() {
		return flowForm;
	}
	public void setFlowForm(FlowForm flowForm) {
		this.flowForm = flowForm;
	}
}
