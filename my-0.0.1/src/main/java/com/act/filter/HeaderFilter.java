
package com.act.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author an_chuantong
 * 
 */

public class HeaderFilter implements Filter {


	private FilterConfig _filterConfig;
	public void destroy() {
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		Enumeration<String> enu = _filterConfig.getInitParameterNames();
		while (enu.hasMoreElements()) {
			String name = enu.nextElement();
			String value = _filterConfig.getInitParameter(name);
			response.addHeader(name, value);
		}
		filterChain.doFilter(req, res);

	}

	
	public void init(FilterConfig filterConfig) {
		this._filterConfig = filterConfig;
		
	}

}
