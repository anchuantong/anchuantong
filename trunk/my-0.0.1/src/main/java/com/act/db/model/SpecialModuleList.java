package com.act.db.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "special_module_list")
public class SpecialModuleList implements Serializable {

	/**
         * 
         */
        private static final long serialVersionUID = -4732097516374008156L;
        
        @Id
	@GeneratedValue
	private Integer id;
        
        private String title;
        
        private String href;
        
        private String picture;
        
        private String created;
        
        private int pos;
        
        @ManyToOne
	@JoinColumn(name="module")
	private SpecialModule module;

	
        public Integer getId() {
        	return id;
        }

	
        public void setId(Integer id) {
        	this.id = id;
        }

	
        public String getTitle() {
        	return title;
        }

	
        public void setTitle(String title) {
        	this.title = title;
        }

	
        public String getHref() {
        	return href;
        }

	
        public void setHref(String href) {
        	this.href = href;
        }

	
        public String getPicture() {
        	return picture;
        }

	
        public void setPicture(String picture) {
        	this.picture = picture;
        }

	
        public String getCreated() {
        	return created;
        }

	
        public void setCreated(String created) {
        	this.created = created;
        }

	
        public int getPos() {
        	return pos;
        }

	
        public void setPos(int pos) {
        	this.pos = pos;
        }

	
        public SpecialModule getModule() {
        	return module;
        }

	
        public void setModule(SpecialModule module) {
        	this.module = module;
        }

}
