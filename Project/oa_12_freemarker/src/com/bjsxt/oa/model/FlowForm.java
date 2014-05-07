package com.bjsxt.oa.model;

import java.util.Set;

/**
 * 流程表单
 * @author Administrator
 *
 */
public class FlowForm {

	private int id;
	
	/**
	 * 对应的工作流
	 */
	private Workflow workflow;
	
	/**
	 * 表单模板
	 */
	private String template;
	
	/**
	 * 流程包含的表单域
	 */
	private Set<FormField> fields;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Workflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Set<FormField> getFields() {
		return fields;
	}
	public void setFields(Set<FormField> fields) {
		this.fields = fields;
	}
}
