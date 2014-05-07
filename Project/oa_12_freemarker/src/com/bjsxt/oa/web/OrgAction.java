package com.bjsxt.oa.web;

import java.util.List;

import com.bjsxt.oa.PagerModel;
import com.bjsxt.oa.manager.OrgManager;
import com.bjsxt.oa.model.Organization;
import com.opensymphony.xwork2.ActionSupport;

public class OrgAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private OrgManager orgManager;
	
	private Organization orgForm = new Organization();
	private List<Organization> orgs;
	private int parentId = 0;
	private int ppid = 0;
	private int orgId;
	
	// 如果select=true，表示机构管理作为一个复用的模块供其他模块使用，
	// 比如人员管理的机构选择，此时应该调用另外一个主界面
	private boolean select;
	
	// 这里需要new一个pagermodel，是因为，如果参数中没有指定一些获取记录的必要参数，
	// 比如第一条记录的位置，每页显示的记录数，这样可以取默认值，而不会抛出空指针错误
	private PagerModel<Organization> pager = new PagerModel<Organization>();
	
	// 每页显示的记录数.因为该项目使用了pager-tagslib.jar，他的默认值是10。
	// 这里的作用是向数据库说明要获取的记录数。
	// 如果要更改页面显示的记录数，这里要改，页面上的pg:pager标签的maxPageItems属性也要改
	
	// 注：分页参数最终的解决方案是通过ThreadLocal传递进来，因此这里不需要指定了
	//private static final int PAGE_SIZE = 10; 
	
	@Override
	public String execute() throws Exception {
		
		// 获取页面数据
		// 第2、3个参数分别是从第几条记录开始、要取出几条，返回的数据都放在PageModel的List中
		// 注：这里的pager在orgmanager中是一个新的对象，因此，在当前的这个action存的值，返回之后已经都不存在了
		// 		这里也是考虑到pg:pager只需要offset和total两个值，因此采取这样的设计
		pager = orgManager.findOrgs(parentId);
		
		// 这里获取ppid的值，用于页面上“返回”这个链接
		if(parentId != 0) {
			Organization parent = orgManager.findOrg(parentId);
			if(parent != null && parent.getParent() != null) {
				ppid = parent.getParent().getId();
			}
		}
		
		// 给人员管理模块，点击选择机构的时候用
		if(this.isSelect()) {
			return "select_org";
		}
		
		return "index";
	}
	public String addInput() throws Exception {
		return "add_input";
	} 
	public String add() throws Exception {
		orgManager.addOrg(orgForm, parentId);
		return "pub_add_success";
	}
	public String del() throws Exception {
		orgManager.delOrg(orgId);
		return "pub_del_success";
	}
	
	// TODO getters and setters...
	public OrgManager getOrgManager() {
		return orgManager;
	}
	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}
	public Organization getOrgForm() {
		return orgForm;
	}
	public void setOrgForm(Organization orgForm) {
		this.orgForm = orgForm;
	}
	public List<Organization> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<Organization> orgs) {
		this.orgs = orgs;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getPpid() {
		return ppid;
	}
	public void setPpid(int ppid) {
		this.ppid = ppid;
	}
	public PagerModel<Organization> getPager() {
		return pager;
	}
	public void setPager(PagerModel<Organization> pager) {
		this.pager = pager;
	}
	public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	

}
