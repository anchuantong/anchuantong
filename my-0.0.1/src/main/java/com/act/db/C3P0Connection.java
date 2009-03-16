
package com.act.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Connection {

	private ComboPooledDataSource dataSource;
	private static C3P0Connection instance;
	private Properties prop;
	private C3P0Connection(){
		InputStream is=getClass().getResourceAsStream("/db-config.properties");
		prop=new Properties();
		try {
	                prop.load(is);
                } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
		dataSource=new ComboPooledDataSource();
		
	}
	
	/**
	 * 得到唯一实例管理类
	 * 
	 * @return
	 */
	static synchronized public C3P0Connection getInstance() {
		if (instance == null) {
			instance = new C3P0Connection();
		}
		return instance;

	}
}
