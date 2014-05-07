package com.bjsxt.oa.manager;

import java.security.acl.Acl;
import java.util.List;

import com.bjsxt.oa.model.Module;

public interface AclManager {
	
	/**
	 * 授权
	 * @param principalType 主体类型
	 * @param principalId 主体标识
	 * @param moduleId 模块标识
	 * @param permission 操作标识（C/R/U/D）
	 * @param yes true表示允许、false表示不允许
	 */
	public void addOrUpdatePermission(
			String principalType,
			int principalId,
			int moduleId,
			int permission,
			boolean yes);
	
	/**
	 * 删除授权
	 * @param principalType 主体类型
	 * @param principalId 主体标识
	 * @param moduleId 模块标识
	 */
	public void delPermission(String principalType,int principalId,int moduleId);
	
	/**
	 * 设置用户授权的继承特性
	 * @param userId 用户ID
	 * @param moduleId 模块ID
	 * @param yes true表示继承【无效】，false表示不继承【有效】
	 */
	public void addOrUpdateUserExtends(int userId,int moduleId,boolean yes);
	
	/**
	 * 即时判断用户对某个模块的某个操作是否允许
	 * @param userId 用户ID
	 * @param moduleId 模块ID
	 * @param permission 操作类型（C/R/U/D）
	 * @return 如果允许，返回true；否则返回false
	 */
	public boolean hasPermission(int userId,int moduleId,int permission);
	
	/**
	 * 即时判断用户对某个模块的某个操作是否允许
	 * @param userId 用户ID
	 * @param moduleId 模块ID
	 * @param permission 操作类型（C/R/U/D）
	 * @return 如果允许，返回true；否则返回false
	 */
	public boolean hasPermissionByResourceSn(int userId,String resourceSn,int permission);
	
	/**
	 * 查询用户拥有读取权限的模块列表（在登录后台管理主界面的时候，需要根据这个列表来生成导航菜单）
	 * @param userId 用户ID
	 * @return 列表的元素是Module对象
	 */
	public List<Module> searchModules(int userId);
	
	/**
	 * 根据主题类型，主题id查找ACL记录
	 * @param principalType 主题类型
	 * @param principalId 主题标识（id）
	 * @return
	 */
	public List<Acl> searchAclRecord(String principalType, int principalId);
}
