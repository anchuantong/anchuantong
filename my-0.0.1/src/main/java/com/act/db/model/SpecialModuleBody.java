package com.act.db.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "special_module_body")
public class SpecialModuleBody  implements Serializable {

	/**
         * 
         */
        private static final long serialVersionUID = -4732097516374008156L;
        
        @Id
	@GeneratedValue
	private Integer id;
        
        private String content;
        
        @ManyToOne
	@JoinColumn(name="module")
	private SpecialModule module;

	
        public Integer getId() {
        	return id;
        }

	
        public void setId(Integer id) {
        	this.id = id;
        }

	
        public String getContent() {
        	return content;
        }

	
        public void setContent(String content) {
        	this.content = content;
        }

	
        public SpecialModule getModule() {
        	return module;
        }

	
        public void setModule(SpecialModule module) {
        	this.module = module;
        }

}
