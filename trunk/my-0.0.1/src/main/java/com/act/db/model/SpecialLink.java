package com.act.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "special_link")
public class SpecialLink  implements Serializable {

	/**
         * 
         */
        private static final long serialVersionUID = -2196594101042294046L;
        
        @Id
	@GeneratedValue
	private Integer id;
        
        private String title;
        
        private String href;
        
        private String description;
        
        private Date created;
        
        @ManyToOne
        @JoinColumn(name="special")
        private Special special;
        
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

	
        public String getDescription() {
        	return description;
        }

	
        public void setDescription(String description) {
        	this.description = description;
        }

	
        public Date getCreated() {
        	return created;
        }

	
        public void setCreated(Date created) {
        	this.created = created;
        }

	
        public Special getSpecial() {
        	return special;
        }

	
        public void setSpecial(Special special) {
        	this.special = special;
        }

	

}
