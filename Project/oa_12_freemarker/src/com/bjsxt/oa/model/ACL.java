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
	 * 主题类型：角色或者用户
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
	 * 授权状态：一个int有32位，用后4位表示CRUD操作，位的取值1或者0，表示允许或不允许
	 * @hibernate.property
	 */
	private int aclState;
	
	/**
	 * 授权状态的掩码：用一个int表示授权的继承状态
	 * @hibernate.property
	 */
	private int aclTriState;
	
	/**
	 * ACL的状态为继承（即无效，判断的时候应该判断其所属角色的授权）
	 */
	public static final int ACL_TRI_STATE_EXTENDS = 0xFFFFFFFF;
	
	/**
	 * ACL的状态为不继承（即有效，判断的时候，直接根据aclState判断授权）
	 */
	public static final int ACL_TRI_STATE_UNEXTENDS = 0;
	
	/**
	 * 授权允许
	 */
	public static final int ACL_YES = 1;
	
	/**
	 * 授权不允许
	 */
	public static final int ACL_NO = 0;
	
	/**
	 * 授权不确定
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
	 * 设置ACL的继承状态
	 * @param yes true标识继承，false表示不继承
	 */
	public void setExtends(boolean yes){
		if(yes){
			aclTriState = ACL_TRI_STATE_EXTENDS;
		}else{
			aclTriState = ACL_TRI_STATE_UNEXTENDS;
		}
	}
}
