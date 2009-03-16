package com.act.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.web.util.Config;

/**
 * @author  an_chuantong
 * 
 */

public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {

	/**
         * 
         */
        private static final long serialVersionUID = 820604638023804552L;

        protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        	request.setCharacterEncoding(Config.getSysEncoding());
        	super.doService(request, response);
        }
}
