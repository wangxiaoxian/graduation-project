package com.bjsxt.oa.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.bjsxt.oa.SystemContext;

/**
 * 向ThreadLocal的变量写入值，在AbstractManager中从ThreadLocal取值
 * 从而剔除了，在action层与manager层传递分页的参数，简化接口
 * @author Administrator
 *
 */
public class PagerFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		SystemContext.setOffset(getOffset(httpRequest));
		SystemContext.setPagesize(getPagesize(httpRequest));
		
		try {
			chain.doFilter(request, response);
		} finally {
			// 清空ThreadLocal中的值
			SystemContext.removeOffset();
			SystemContext.removePagesize();
		}
		
	}
	
	public int getOffset(HttpServletRequest httpRequest) {
		int offset = 0;
		try {
			offset = Integer.parseInt(httpRequest.getParameter("pager.offset"));
		} catch (NumberFormatException ignore) {
			// 如果发生转换异常，则默认为0，不进行异常处理
		}
		return offset;
	}
	
	/**
	 * pagesize一般都是从session中获取的
	 * @param httpRequest
	 * @return
	 */
	public int getPagesize(HttpServletRequest httpRequest) {
		return 10;
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
