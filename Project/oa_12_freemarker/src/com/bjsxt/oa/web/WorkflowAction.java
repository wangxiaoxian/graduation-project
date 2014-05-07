package com.bjsxt.oa.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.io.SAXReader;

import com.bjsxt.oa.manager.WorkflowManager;
import com.bjsxt.oa.model.Workflow;
import com.bjsxt.oa.util.FileTypeConverter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class WorkflowAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private WorkflowManager workflowManager;
	
	private int workflowId;
	
	private File processDef;
	private String processDefContentType;
    private String processDefFilename;
    
	private File processImage;
	private String processImageContentType;
	private String processImageFilename;
	
	private List<Workflow> workflows;
	private Workflow workflow;
	
	// 流程定义文件以字符串的形式展示到页面上
	private String def;
	@Override
	public String execute() throws Exception {
		
		workflows = workflowManager.searchAllWorkflows();
		
		return "index";
	}
	
	// 添加流程定义
	public String add() throws Exception {
		workflowManager.addOrUpdateWorkflow(
				FileTypeConverter.getBytesFromFile(processDef), 
				FileTypeConverter.getBytesFromFile(processImage));
		return "add_success";
	}
	
	// 删除流程定义
	public String del() throws Exception {
		
		workflowManager.delWorkflow(workflowId);
		
		return "pub_del_success";
	}
	
	// 打开查看流程图片的界面
	public String viewImage() throws Exception {
		workflow = workflowManager.findWorkflow(workflowId);
		
		return "view_image";
	}
	
	// 查看图片(次请求由flow_image.jsp中的<img src="">发出)
	public String image() throws Exception {
		Workflow wf = workflowManager.findWorkflow(workflowId);
		
		HttpServletResponse respone = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		respone.setContentType("image/jpeg");
		respone.getOutputStream().write(wf.getProcessImage());
		
		return null;
	}

	//查看流程定义界面
	public String viewDef() throws Exception {

		workflow = workflowManager.findWorkflow(workflowId);
		byte[] processDef = workflow.getProcessDef();
		
		// 将byte[]数组转换为字符串
		// String def = new String(processDef, "UTF-8");
		
		// 为了避免硬编码encoding，可以利用dom4j来帮助我们转换xml文件
		def = new SAXReader().read(new ByteArrayInputStream(processDef)).asXML();
		
		return "view_def";
	}
	
	// TODO getters and setters...
	public File getProcessDef() {
		return processDef;
	}
	public void setProcessDef(File processDef) {
		this.processDef = processDef;
	}
	public String getProcessDefContentType() {
		return processDefContentType;
	}
	public void setProcessDefContentType(String processDefContentType) {
		this.processDefContentType = processDefContentType;
	}
	public String getProcessDefFilename() {
		return processDefFilename;
	}
	public void setProcessDefFilename(String processDefFilename) {
		this.processDefFilename = processDefFilename;
	}
	public File getProcessImage() {
		return processImage;
	}
	public void setProcessImage(File processImage) {
		this.processImage = processImage;
	}
	public String getProcessImageContentType() {
		return processImageContentType;
	}
	public void setProcessImageContentType(String processImageContentType) {
		this.processImageContentType = processImageContentType;
	}
	public String getProcessImageFilename() {
		return processImageFilename;
	}
	public void setProcessImageFilename(String processImageFilename) {
		this.processImageFilename = processImageFilename;
	}
	public void setWorkflowManager(WorkflowManager workflowManager) {
		this.workflowManager = workflowManager;
	}
	public List<Workflow> getWorkflows() {
		return workflows;
	}
	public void setWorkflows(List<Workflow> workflows) {
		this.workflows = workflows;
	}
	public int getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
	}
	public String getDef() {
		return def;
	}
	public Workflow getWorkflow() {
		return workflow;
	}
}
