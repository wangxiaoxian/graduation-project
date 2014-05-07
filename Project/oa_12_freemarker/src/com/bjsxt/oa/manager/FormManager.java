package com.bjsxt.oa.manager;

import java.util.List;

import com.bjsxt.oa.model.FieldInput;
import com.bjsxt.oa.model.FieldItem;
import com.bjsxt.oa.model.FieldType;
import com.bjsxt.oa.model.FlowForm;
import com.bjsxt.oa.model.FormField;

/**
 * 表单管理器
 * @author Lee
 *
 */
public interface FormManager {
	
	//表单
	public void addForm(FlowForm form,int workflowId);
	public void delForm(int formId);
	public FlowForm findForm(int workflowId);
	public List searchAllForms();
	
	//表单域
	public void addField(FormField field,int formId,int fieldTypeId,int fieldInputId);
	public void delField(int fieldId);
	public FormField findFormField(int fieldId);
	public List searchAllFields(int formId);

	//表单域类型
	public FieldType findFieldType(int typeId);
	public List searchFieldTypes();
	
	//表单域输入框
	public FieldInput findFieldInput(int inputId);
	public List searchFieldInputs();
	
	public void updateFieldItems(int fieldId,List items);
}
