package com.act.db;


/**
 * <p>
 * Title: 数据库连接池
 * </p>
 * @author <a href="mailto:anchuantong@yahoo.com.cn">anchuantong</a>
 * @version 1.0
 * 
 */
public class DBConnectionPool {

	/** 数据库驱动*/
	private String driver;
	
	private String url;
	
	private String username;
	
	private String password;
	
	private Integer maxConn;
	
	private Integer minConn;
	
	private Integer maxWait;

	
	
	
        public String getDriver() {
        	return driver;
        }

	
        public void setDriver(String driver) {
        	this.driver = driver;
        }

	
        public String getUrl() {
        	return url;
        }

	
        public void setUrl(String url) {
        	this.url = url;
        }

	
        public String getUsername() {
        	return username;
        }

	
        public void setUsername(String username) {
        	this.username = username;
        }

	
        public String getPassword() {
        	return password;
        }

	
        public void setPassword(String password) {
        	this.password = password;
        }

	
        public Integer getMaxConn() {
        	return maxConn;
        }

	
        public void setMaxConn(Integer maxConn) {
        	this.maxConn = maxConn;
        }

	
        public Integer getMinConn() {
        	return minConn;
        }

	
        public void setMinConn(Integer minConn) {
        	this.minConn = minConn;
        }

	
        public Integer getMaxWait() {
        	return maxWait;
        }

	
        public void setMaxWait(Integer maxWait) {
        	this.maxWait = maxWait;
        }
	
	
}
