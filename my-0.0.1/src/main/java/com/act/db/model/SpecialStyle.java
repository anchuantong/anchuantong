package com.act.db.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "special")
public class SpecialStyle implements Serializable {

	/**
         * 
         */
        private static final long serialVersionUID = 2762508365820730958L;
        
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="special")
	private Special special;

	
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

	
        public String getContent() {
        	return content;
        }

	
        public void setContent(String content) {
        	this.content = content;
        }

	
        public Special getSpecial() {
        	return special;
        }

	
        public void setSpecial(Special special) {
        	this.special = special;
        }
	

}
