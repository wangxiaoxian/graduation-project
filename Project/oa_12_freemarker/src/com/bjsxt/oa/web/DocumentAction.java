package com.bjsxt.oa.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.DocumentManager;
import com.bjsxt.oa.manager.WorkflowManager;
import com.bjsxt.oa.model.ApproveInfo;
import com.bjsxt.oa.model.Document;
import com.bjsxt.oa.model.User;
import com.bjsxt.oa.model.Workflow;
import com.bjsxt.oa.util.FileTypeConverter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DocumentAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private DocumentManager documentManager;
	private WorkflowManager workflowManager;

	private PagerModel pm;
	private List<Document> documents = new ArrayList<Document>();
	private Document document;
	
	private File contentFile;
	private String contentFileContentType;
    private String contentFileFilename;
    
    // 动态表单的属性
    private Map<String, String> props;
    
	// 审批信息
	private ApproveInfo approveInfo;
	
	//下一步流向列表
	private List steps = new ArrayList();
	
	//下一步流向的名称
	private String transitionName;
	
	//审批历史列表
	private List<ApproveInfo> historys = new ArrayList<ApproveInfo>();
	
	// 只传递一个id参数的时候可以使用这个变量，避免又生成一个对象
	private int documentId;
	private int workflowId;

	//添加公文的时候，需要选择哪个流程
	private List<Workflow> workflows = new ArrayList<Workflow>();
	
	/**
	 * 打开公文管理的主界面
	 */
	@Override
	public String execute() throws Exception {
		
		pm = documentManager.searchMyDocuments(currentUser().getId());
		return "index";
	}

	/**
	 * 已审核的公文列表, 显示由当前登录人员审核的公文列表
	 * @return
	 * @throws Exception
	 */
	public String approvedList() throws Exception {
		pm = documentManager.searchApprovedDocuments(currentUser().getId());
		return "approved_list";
	}
	
	/**
	 * 待审核的公文列表
	 * @return
	 * @throws Exception
	 */
	public String approvingList() throws Exception {
		documents = documentManager.searchApprovingDocuments(currentUser().getId());
		return "approving_list";
	}
	
	/**
	 * 在待审公文列表上，针对某个文档，可以点击打开审批界面，对此文档进行审批
	 * @return
	 */
	public String approveInput() throws Exception {
		
		return "approve_input";
	}
	
	/**
	 * 用户输入审批信息后，点击保存，对文档进行审批操作
	 * @return
	 */
	public String approve() throws Exception {
		int approverId = currentUser().getId();
		
		approveInfo.setApproveTime(new Date());
		
		documentManager.addApproveInfo(approveInfo, documentId, approverId);
		return "pub_add_success";
	}
	
	/**
	 * 查看公文的审批历史
	 * @return
	 * @throws Exception
	 */
	public String approvedHistory() throws Exception {
		
		historys = documentManager.searchApproveInfos(documentId);
		return "approved_history";
	}
	
	/**
	 * 在我的公文或待审公文列表上，点击提交，可打开提交的选择界面
	 * 在这个界面上，列出下一步所有可选的步骤，用户可以选择其中一个步骤进行提交操作
	 * 系统将按照用户的选择转移到下一个节点
	 * @return
	 */
	public String submitInput() throws Exception {
		
		steps = documentManager.searchNextSteps(documentId, currentUser().getId());
		return "submit_input";
	}
	
	/**
	 * 用户选择了其中一个步骤，点击提交
	 * @return
	 */
	public String submit() throws Exception {
		
		documentManager.submitToWorkflow(currentUser().getId(), documentId, transitionName);
		return "pub_add_success";
	}
	
	/**
	 * 删除公文
	 * @return
	 * @throws Exception
	 */
	public String del() throws Exception {
		
		documentManager.delDocument(documentId);
		return "pub_del_success";
	}
	
	/**
	 * 点击添加公文的时候，需要选择相应的流程，此界面列出所有的流程以供选择
	 * @return
	 * @throws Exception
	 */
	public String selectFlow() throws Exception {
		workflows = workflowManager.searchAllWorkflows();
		return "select_flow";
	}
	
	/**
	 * 选择了流程之后（即点击流程名称），需要打开公文录入界面
	 * @return
	 * @throws Exception
	 */
	public String addInput() throws Exception {
	
		return "add_input";
	}
	
	/**
	 * 添加公文的操作
	 * @return
	 */
	public String add() throws Exception {
		if (contentFile != null) {
			document.setContent(FileTypeConverter.getBytesFromFile(contentFile));
		}
		documentManager.addDocument(document, workflowId, currentUser().getId(), props);
		return "pub_add_success";
	}
	
	/**
	 * 下载公文附件
	 * @return
	 * @throws Exception
	 */
	public String download() throws Exception {
		// 使用原始的方式处理下载
		Document document = documentManager.findDocument(documentId);
		
		ActionContext ctx = ActionContext.getContext();       
	      
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE); 
		
		response.reset();
		response.setContentType("application/x-download;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=temp.doc");
		
		response.getOutputStream().write(document.getContent());
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
		// 指示struts，不要对返回值进行处理
		return null;
	}
	
	/**
	 * 从session中拿出当前用户
	 * @return
	 */
	private User currentUser() throws Exception {
		
		return (User) ActionContext.getContext().getSession().get("login");
	}

	// TODO getters and setters...
	public void setDocumentManager(DocumentManager documentManager) {
		this.documentManager = documentManager;
	}
	public void setWorkflowManager(WorkflowManager workflowManager) {
		this.workflowManager = workflowManager;
	}
	public PagerModel getPm() {
		return pm;
	}
	public void setPm(PagerModel pm) {
		this.pm = pm;
	}
	public List<Document> getDocuments() {
		return documents;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public ApproveInfo getApproveInfo() {
		return approveInfo;
	}
	public void setApproveInfo(ApproveInfo approveInfo) {
		this.approveInfo = approveInfo;
	}
	public List getSteps() {
		return steps;
	}
	public String getTransitionName() {
		return transitionName;
	}
	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}
	public List<ApproveInfo> getHistorys() {
		return historys;
	}
	public int getDocumentId() {
		return documentId;
	}
	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	public List<Workflow> getWorkflows() {
		return workflows;
	}
	public File getContentFile() {
		return contentFile;
	}
	public void setContentFile(File contentFile) {
		this.contentFile = contentFile;
	}
	public String getContentFileContentType() {
		return contentFileContentType;
	}
	public void setContentFileContentType(String contentFileContentType) {
		this.contentFileContentType = contentFileContentType;
	}
	public String getContentFileFilename() {
		return contentFileFilename;
	}
	public void setContentFileFilename(String contentFileFilename) {
		this.contentFileFilename = contentFileFilename;
	}
	public int getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
	}
	public Map<String, String> getProps() {
		return props;
	}
	public void setProps(Map<String, String> props) {
		this.props = props;
	}
}
