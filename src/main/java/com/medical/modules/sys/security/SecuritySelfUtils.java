package com.medical.modules.sys.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * SecurityUtils的扩展类（添加验证某个权限的方法）
 * @author Administrator
 *
 */
public class SecuritySelfUtils extends SecurityUtils {
	
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 验证是否拥有某个权限
	 * @param permissions
	 * @return
	 */
	public static boolean hasAnyPermission(String permissions){
		Subject subject = getSubject();
		String[] perms = null;
		if (permissions.indexOf(",") > 0) {
			perms = permissions.split(",");
		} else {
			perms = new String[]{permissions};
		}
		for (String p : perms) {
			if (subject.isPermitted(p)) {
				return true;
			}
		}
		return false;
	}

}
