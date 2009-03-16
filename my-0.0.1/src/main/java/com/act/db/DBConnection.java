
package com.act.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>
 * Title: 数据库管理类
 * </p>
 * <p>
 * Description: 管理类DBConnection从连接池中获得有效连接
 * </p>
 * 
 * @author <a href="mailto:anchuantong@yahoo.com.cn">anchuantong</a>
 * @version 1.0
 * 
 */

public class DBConnection {

	private static DBConnection instance;
	private Properties prop;
	/**
	 * 初始化
	 */
	public DBConnection() {
		InputStream is=getClass().getResourceAsStream("/db-config.properties");
		prop=new Properties();
		try {
	                prop.load(is);
                } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
                
		
	}

	/**
	 * 得到唯一实例管理类
	 * 
	 * @return
	 */
	static synchronized public DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;

	}
}
