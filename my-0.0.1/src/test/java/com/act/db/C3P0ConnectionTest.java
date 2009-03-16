package com.act.db;

import junit.framework.TestCase;


public class C3P0ConnectionTest extends TestCase {
	private C3P0Connection connection;
	public void testOne(){
		connection=C3P0Connection.getInstance();
		System.out.println(connection.getInstance());
	}

}
