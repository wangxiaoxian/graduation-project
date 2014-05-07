package com.bjsxt.oa.model;

/**
 * 
 * @author Administrator
 * @hibernate.class table="T_ACL"
 */
public class ACL {
	
	public static final String TYPE_ROLE = "Role";
	public static final String TYPE_USER = "User";
	
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * �������ͣ���ɫ�����û�
	 * @hibernate.property
	 */
	private String principalType;
	
	/**
	 * @hibernate.property
	 */
	private int principalId;
	
	/**
	 * @hibernate.property
	 */
	private int moduleId;
	
	/**
	 * ��Ȩ״̬��һ��int��32λ���ú�4λ��ʾCRUD������λ��ȡֵ1����0����ʾ���������
	 * @hibernate.property
	 */
	private int aclState;
	
	/**
	 * ��Ȩ״̬�����룺��һ��int��ʾ��Ȩ�ļ̳�״̬
	 * @hibernate.property
	 */
	private int aclTriState;
	
	/**
	 * ACL��״̬Ϊ�̳У�����Ч���жϵ�ʱ��Ӧ���ж���������ɫ����Ȩ��
	 */
	public static final int ACL_TRI_STATE_EXTENDS = 0xFFFFFFFF;
	
	/**
	 * ACL��״̬Ϊ���̳У�����Ч���жϵ�ʱ��ֱ�Ӹ���aclState�ж���Ȩ��
	 */
	public static final int ACL_TRI_STATE_UNEXTENDS = 0;
	
	/**
	 * ��Ȩ����
	 */
	public static final int ACL_YES = 1;
	
	/**
	 * ��Ȩ������
	 */
	public static final int ACL_NO = 0;
	
	/**
	 * ��Ȩ��ȷ��
	 */
	public static final int ACL_NEUTRAL = -1;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrincipalType() {
		return principalType;
	}
	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}
	public int getPrincipalId() {
		return principalId;
	}
	public void setPrincipalId(int principalId) {
		this.principalId = principalId;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public int getAclState() {
		return aclState;
	}
	public void setAclState(int aclState) {
		this.aclState = aclState;
	}
	public int getAclTriState() {
		return aclTriState;
	}
	public void setAclTriState(int aclTriState) {
		this.aclTriState = aclTriState;
	}
	
	public void setPermission(int permission,boolean yes){
		int temp = 1;
		temp = temp << permission;
		if(yes){
			aclState |= temp;
		}else{
			aclState &= ~temp;
		}
	}
	
	public int getPermission(int permission){
		if(aclTriState == ACL_TRI_STATE_EXTENDS){
			return ACL_NEUTRAL;
		}
		
		int temp = 1;
		temp = temp << permission;
		temp &= aclState;
		if(temp != 0){
			return ACL_YES;
		}
		return ACL_NO;
	}
	
	/**
	 * ����ACL�ļ̳�״̬
	 * @param yes true��ʶ�̳У�false��ʾ���̳�
	 */
	public void setExtends(boolean yes){
		if(yes){
			aclTriState = ACL_TRI_STATE_EXTENDS;
		}else{
			aclTriState = ACL_TRI_STATE_UNEXTENDS;
		}
	}
}
