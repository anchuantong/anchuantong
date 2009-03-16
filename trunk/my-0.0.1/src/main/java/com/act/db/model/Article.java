package com.act.db.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.OrderBy;

@Entity
@Table(name="article")
public class Article implements Serializable {

	/**
         * 
         */
        private static final long serialVersionUID = 6617356720894719416L;

        @Id
        @GeneratedValue
        private Integer id;
        
        private String title;
        
        private String path;
        
        @ManyToOne
        @JoinColumn(name="category")
        private Category category;
        
        private String description;
        
       
        private String creator;
        
        private Date created;
        
        private String modifer;
        
        private Date modifed;
        
        private Integer hits;
        
        private boolean featured;
        
        private boolean published;
        
        @OneToMany
        @Cascade(CascadeType.DELETE_ORPHAN)
        @IndexColumn(name="pos")
        @JoinColumn(name = "article", unique = false,insertable=true) 
        @OrderBy(clause="pos asc")
        // @JoinTable(inverseJoinColumns=@JoinColumn(name="article"))
        private List<ArticlePart> parts;


	
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


	
        public String getPath() {
        	return path;
        }


	
        public void setPath(String path) {
        	this.path = path;
        }


	
        public Category getCategory() {
        	return category;
        }


	
        public void setCategory(Category category) {
        	this.category = category;
        }


	
        public String getDescription() {
        	return description;
        }


	
        public void setDescription(String description) {
        	this.description = description;
        }


	
        public String getCreator() {
        	return creator;
        }


	
        public void setCreator(String creator) {
        	this.creator = creator;
        }


	
        public Date getCreated() {
        	return created;
        }


	
        public void setCreated(Date created) {
        	this.created = created;
        }


	
        public String getModifer() {
        	return modifer;
        }


	
        public void setModifer(String modifer) {
        	this.modifer = modifer;
        }


	
        public Date getModifed() {
        	return modifed;
        }


	
        public void setModifed(Date modifed) {
        	this.modifed = modifed;
        }


	
        public Integer getHits() {
        	return hits;
        }


	
        public void setHits(Integer hits) {
        	this.hits = hits;
        }


	
        public boolean isFeatured() {
        	return featured;
        }


	
        public void setFeatured(boolean featured) {
        	this.featured = featured;
        }


	
        public boolean isPublished() {
        	return published;
        }


	
        public void setPublished(boolean published) {
        	this.published = published;
        }


	
        public List<ArticlePart> getParts() {
        	return parts;
        }


	
        public void setParts(List<ArticlePart> parts) {
        	this.parts = parts;
        }
        
        public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
