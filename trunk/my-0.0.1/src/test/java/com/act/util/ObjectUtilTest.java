
package com.act.util;

import java.util.Date;

import junit.framework.TestCase;

public class ObjectUtilTest extends TestCase {

	public  void testSetProperties() throws Exception

	{
		User user=new User();
		user.setAddress("ÎÄ¶şÂ·");
		user.setName("anchuantong");
		
		Bean bean1=new Bean();
		Bean bean2=new Bean();
		bean1.setCreated(new Date());
		bean1.setId(133);
		bean1.setName("ananan");
		bean1.setUser(user);
		ObjectUtil.getInstance().copyObject(bean1,bean2);
		
		System.out.println("after id:"+bean2.getName());
		System.out.println("after id:"+ObjectUtil.getInstance().toXml(bean1, "fds"));
	}
	
	class Bean{
		private Integer id;
		
		private String name;
		
		private Date created;
		
		private User user;
		
                
                public User getUser() {
                	return user;
                }


		
                public void setUser(User user) {
                	this.user = user;
                }


		public Integer getId() {
                	return id;
                }

		
                public void setId(Integer id) {
                	this.id = id;
                }

		
                public String getName() {
                	return name;
                }

		
                public void setName(String name) {
                	this.name = name;
                }

		
                public Date getCreated() {
                	return created;
                }

		
                public void setCreated(Date created) {
                	this.created = created;
                }
	}
	
	class User{
		private String name;
		
		private String address;

		
                public String getName() {
                	return name;
                }

		
                public void setName(String name) {
                	this.name = name;
                }

		
                public String getAddress() {
                	return address;
                }

		
                public void setAddress(String address) {
                	this.address = address;
                }
	}
}
