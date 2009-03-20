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
     
        private String created;
        
        private int pos;
        
        private Integer objectId;
        
        

	private Integer objectType;
        
        
        @ManyToOne
	@JoinColumn(name="module")
	private SpecialModule module;

	
        public Integer getId() {
        	return id;
        }

	
        public void setId(Integer id) {
        	this.id = id;
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

        public Integer getObjectId() {
        	return objectId;
        }


	
        public void setObjectId(Integer objectId) {
        	this.objectId = objectId;
        }


	
        public Integer getObjectType() {
        	return objectType;
        }


	
        public void setObjectType(Integer objectType) {
        	this.objectType = objectType;
        }

}
