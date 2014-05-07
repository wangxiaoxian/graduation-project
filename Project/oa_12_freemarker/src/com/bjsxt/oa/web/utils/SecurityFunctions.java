package com.bjsxt.oa.web.utils;

import com.bjsxt.oa.manager.AclManager;

public class SecurityFunctions {

	private static AclManager aclManager;
	
	public static boolean hasPermission(int userId, String resourceSn, int permission) {
		
		return aclManager.hasPermissionByResourceSn(userId, resourceSn, permission);
	}

	public void setAclManager(AclManager aclManager) {
		SecurityFunctions.aclManager = aclManager;
	}
}
