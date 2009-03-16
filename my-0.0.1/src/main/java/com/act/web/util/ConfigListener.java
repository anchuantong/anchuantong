
package com.act.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;

public class ConfigListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {

		Properties props = new Properties();
		try {
			File conf = new File(event.getServletContext().getRealPath("WEB-INF/conf/velocity.properties"));
			props.load(new FileInputStream(conf));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Iterator<Object> iter = props.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				Velocity.setProperty(key, props.getProperty(key));
			}
			Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
			Velocity.setProperty("runtime.log.logsystem.log4j.category", "sys.velocity");
			Velocity.setProperty("resource.loader", "file");
			Velocity.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, event.getServletContext().getRealPath("/WEB-INF/templates"));
			Velocity.setProperty("parser.pool.size", "3");
			Velocity.setProperty("velocimacro.library", "macro_default.vm,macro_act.vm");
			Velocity.setProperty("input.encoding", Config.getSysEncoding());
			Velocity.setProperty("output.encoding", Config.getSysEncoding());
			Velocity.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

}
