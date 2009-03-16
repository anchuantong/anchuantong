
package com.act.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>
 * Title: ���ݿ������
 * </p>
 * <p>
 * Description: ������DBConnection�����ӳ��л����Ч����
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
	 * ��ʼ��
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
	 * �õ�Ψһʵ��������
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
