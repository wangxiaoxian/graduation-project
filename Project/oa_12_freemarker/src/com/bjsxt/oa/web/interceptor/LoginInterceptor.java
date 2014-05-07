package com.bjsxt.oa.web.interceptor;

import com.bjsxt.oa.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		User user = (User)ActionContext.getContext().getSession().get("login");
		if (user == null) {
			return "login";
		}
		return invocation.invoke();
	}

}
