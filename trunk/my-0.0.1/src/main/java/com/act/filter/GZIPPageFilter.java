
package com.act.filter;

import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.act.web.includeservletasstring.BufferOutputStream;
import com.act.web.includeservletasstring.BufferedResponse;

public class GZIPPageFilter implements Filter {
	private static final Log logger = LogFactory.getLog(GZIPPageFilter.class);
	private FilterConfig filterConfig;

	// Handle the passed-in FilterConfig
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	// Process the request/response pair
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws javax.servlet.ServletException, java.io.IOException {
		BufferedResponse wrapper = new BufferedResponse((HttpServletResponse) res);
		filterChain.doFilter(req, wrapper);
		OutputStream out = res.getOutputStream();
		String encodings = ((HttpServletRequest) req).getHeader("Accept-Encoding");
		if (encodings != null && encodings.indexOf("gzip") != -1&&wrapper.getBufferAsByteArray().length>0) {
			BufferOutputStream compressed = new BufferOutputStream();
			GZIPOutputStream gzout = new GZIPOutputStream(compressed);
			gzout.write(wrapper.getBufferAsByteArray());
			gzout.flush();
			gzout.close();
			((HttpServletResponse) res).setHeader("Content-Encoding", "gzip");
			((HttpServletResponse) res).setHeader("Vary", "Accept-Encoding");
			out.write(compressed.getContentsAsByteArray());
		} else {
			out.write(wrapper.getBufferAsByteArray());
		}
		out.flush();
		out.close();
	}

	// Clean up resources
	public void destroy() {
	}
}
