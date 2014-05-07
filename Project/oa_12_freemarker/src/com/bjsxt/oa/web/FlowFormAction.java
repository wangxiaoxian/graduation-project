package com.bjsxt.oa.web;

import java.util.Iterator;
import java.util.List;

import com.bjsxt.oa.manager.FormManager;
import com.bjsxt.oa.manager.WorkflowManager;
import com.bjsxt.oa.model.FieldInput;
import com.bjsxt.oa.model.FieldItem;
import com.bjsxt.oa.model.FieldType;
import com.bjsxt.oa.model.FlowForm;
import com.bjsxt.oa.model.FormField;
import com.bjsxt.oa.model.Workflow;
import com.opensymphony.xwork2.ActionSupport;

public class FlowFormAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private FormManager formManager;
	private WorkflowManager workflowManager;
	
	private int workflowId;
	private int fieldTypeId;
	private int fieldInputId;
	private int formFieldId;
	private int flowFormId;
	
	private Workflow workflow;
	private FlowForm flowForm;
	private FormField formField;
	
	private List<Workflow> workflows;
	private List<FieldType> fieldTypes;
	private List<FieldInput> fieldInputs;
	private List<FieldItem> items;
	
	//主界面，显示所有的流程信息
	@Override
	public String execute() throws Exception {
		
		//获取所有流程信息进行显示
		workflows = workflowManager.searchAllWorkflows();
		
		return "index";
	}
	
	//打开某个流程表单的定义界面
	public String addFormInput() throws Exception {
		
		workflow = workflowManager.findWorkflow(workflowId);
		flowForm = formManager.findForm(workflowId);
		
		return "form_input";
	}
	
	//添加流程表单
	public String addForm() throws Exception {
		
		formManager.addForm(flowForm, workflowId);

//		ActionForward forward = new ActionForward();
//		forward.setPath("/flowform.do?method=addFormInput&workflowId="+ffaf.getWorkflowId());
//		forward.setRedirect(true);
		return "add_ok";
	}
	
	//打开界面，输入表单域，在这个界面上，需要选择表单域的类型和输入形式
	public String formFieldInput() throws Exception {
		
		fieldTypes = formManager.searchFieldTypes();
		fieldInputs = formManager.searchFieldInputs();
		
		return "formfield_input";
	}
	
	//添加表单域
	public String addFormField() throws Exception {
		
		formManager.addField(formField, flowFormId, fieldTypeId, fieldInputId);
		
		return "pub_add_success";
	}
	
	//删除表单域
	public String delField() throws Exception {
		
		formManager.delField(formFieldId);
		
		return "pub_del_success";
	}
	
	//给某个表单域添加条目（输入界面），列出已有的条目
	public String addItemInput() throws Exception {
		
		//首先根据ID的值，加载表单域
		formField = formManager.findFormField(formFieldId);
		
		return "item_input";
	}
	
	//添加条目
	public String addItem() throws Exception {
		
		for (Iterator iter = items.iterator(); iter.hasNext();) {
			FieldItem item = (FieldItem) iter.next();
			//如果没有输入任何值，则忽略不处理
			if(item == null || item.getValue() == null || item.getValue().trim().equals("")){
				iter.remove();
			}
		}
		formManager.updateFieldItems(formFieldId, items);
		
		return "pub_add_success";
	}

	// TODO getters and setters ...
	public void setFormManager(FormManager formManager) {
		this.formManager = formManager;
	}
	public void setWorkflowManager(WorkflowManager workflowManager) {
		this.workflowManager = workflowManager;
	}
	public List<Workflow> getWorkflows() {
		return workflows;
	}
	public int getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
	}
	public Workflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	public FlowForm getFlowForm() {
		return flowForm;
	}
	public void setFlowForm(FlowForm flowForm) {
		this.flowForm = flowForm;
	}
	public List<FieldType> getFieldTypes() {
		return fieldTypes;
	}
	public List<FieldInput> getFieldInputs() {
		return fieldInputs;
	}
	public FormField getFormField() {
		return formField;
	}
	public void setFormField(FormField formField) {
		this.formField = formField;
	}
	public int getFieldTypeId() {
		return fieldTypeId;
	}
	public void setFieldTypeId(int fieldTypeId) {
		this.fieldTypeId = fieldTypeId;
	}
	public int getFieldInputId() {
		return fieldInputId;
	}
	public void setFieldInputId(int fieldInputId) {
		this.fieldInputId = fieldInputId;
	}
	public int getFormFieldId() {
		return formFieldId;
	}
	public void setFormFieldId(int formFieldId) {
		this.formFieldId = formFieldId;
	}
	public List<FieldItem> getItems() {
		return items;
	}
	public void setItems(List<FieldItem> items) {
		this.items = items;
	}
	public int getFlowFormId() {
		return flowFormId;
	}
	public void setFlowFormId(int flowFormId) {
		this.flowFormId = flowFormId;
	}
	
}
