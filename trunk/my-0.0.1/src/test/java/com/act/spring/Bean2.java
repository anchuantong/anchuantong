package com.act.spring;

import org.springframework.beans.factory.annotation.Autowired;


public class Bean2 {

        @Autowired
	private Bean1 bean1;

	
        public Bean1 getBean1() {
        	return bean1;
        }

	
        public void setBean1(Bean1 bean1) {
        	this.bean1 = bean1;
        }
}
