package com.bjsxt.oa.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.bjsxt.oa.web.SystemException;
import com.sun.org.apache.commons.beanutils.BeanUtils;
import com.sun.org.apache.commons.beanutils.PropertyUtils;

/**
 * 公文
 * @author Administrator
 *
 */
public class Document {

	public static final String STATUS_NEW = "新建";
	public static final String STATUS_END = "结束";
	
	private int id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 公文内容，即上传文件的内容，
	 * 这些内容将会被保存到数据库
	 */
	private byte[] content;
	
	/**
	 * 创建者
	 */
	private User creator;
	
	/**
	 *  公文的当前状态：
	 *  只有新建状态的公文，才可以被更新和删除；
	 *  只有已完成状态的公文，才可以被归档；
	 */
	private String status;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 公文所走的流程
	 */
	private Workflow workflow;
	
	/**
	 * 流程实例的标识
	 */
	private long processInstanceId;
	
	/**
	 * 表单的动态属性
	 */
	private Map<String, DocumentProperty> properties;
	
	//将未转换的动态属性，进行转换，并设置到properties属性中
	public void setPropertiesMap(Map<String, Object> props){
		if(workflow.getFlowForm() == null){
			return;
		}
		
		Set<FormField> fields = workflow.getFlowForm().getFields();
		for (Iterator<FormField> iterator = fields.iterator(); iterator.hasNext();) {
			FormField field = iterator.next();
			String name = field.getFieldName();
			Object value = props.get(name);
			
			//将value转换为DocumentProperty对象，并添加到properties属性中
			setProperty(name,value);
		}
	}
	
	public void setProperty(String name,Object value){
		try {
			String type = getPropertyType(name); //属性的类型描述
			DocumentProperty dp = new DocumentProperty(); //需要转换的目标
			
			//判断一下是否支持本类型的设置
			if(!DocumentProperty.support(type)){
				throw new SystemException("不支持的数据类型【"+type+"】");
			}
			
			//这个调用，会触发BeanUtils自动调用相应属性的转换器(Converter)
			BeanUtils.copyProperty(dp, type, value);
			
			if(properties == null){
				properties = new HashMap<String, DocumentProperty>();
			}
			
			properties.put(name, dp);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("在设置属性【"+name+"】的时候出现异常",e);
		}
	}
	
	/**
	 * 获取对应的动态属性
	 * @param name 动态属性的名称
	 * @return 动态属性的值
	 */
	public Object getProperty(String name){
		try {
			if(properties == null){
				return null;
			}
			
			DocumentProperty dp = (DocumentProperty)properties.get(name);
			String type = getPropertyType(name);
			return PropertyUtils.getProperty(dp, type);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("在获取公文【"+title+"】的属性【"+name+"】的时候，出现异常");
		}
	}
	
	/**
	 * 获得对应属性的类型名称
	 * @param name 属性名称
	 * @return 属性的类型的名称
	 */
	private String getPropertyType(String name){
		Set<FormField> fields = workflow.getFlowForm().getFields();
		for (Iterator<FormField> iterator = fields.iterator(); iterator.hasNext();) {
			FormField field = iterator.next();
			if(name.equals(field.getFieldName())){
				return field.getFieldType().getType();
			}
		}
		
		throw new SystemException("无法获得对应属性【"+name+"】的类型名称");
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Workflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	public long getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<String, DocumentProperty> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, DocumentProperty> properties) {
		this.properties = properties;
	}
}
