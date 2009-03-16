package com.act.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class Bean {

	private Bean2 bean;

	
        public Bean2 getBean() {
        	return bean;
        }

        @Autowired
        public void setBean(@Qualifier("bean2")Bean2 bean) {
        	this.bean = bean;
        }
        
        public String action(){
        	
        	return "";
        }
	
}
