package com.act.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author  an_chuantong
 * 
 */
@Entity
public class Picture implements Serializable {

	
        /**
         * 
         */
        private static final long serialVersionUID = 7754995324445939141L;
	
        @Id
        @GeneratedValue
        private Integer id;
        
        private String title;
        
        private String path;
        
        private Integer width;
        
        private Integer height;
        
        private Integer size;
        
        private String imagePath;
        
        private String filename;
        
        @ManyToOne
        @JoinColumn(name="category")
        private Category category;
        
        private String description;
        
        @ManyToOne
        private User creator;
        
        private Date created;
        
        @ManyToOne
        private User modifer;
        
        private Date modifed;
        
        private Integer hits;
        
        private boolean featured;
        
        private boolean published;

	
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

	
        public Integer getWidth() {
        	return width;
        }

	
        public void setWidth(Integer width) {
        	this.width = width;
        }

	
        public Integer getHeight() {
        	return height;
        }

	
        public void setHeight(Integer height) {
        	this.height = height;
        }

	
        public Integer getSize() {
        	return size;
        }

	
        public void setSize(Integer size) {
        	this.size = size;
        }

	
        public String getImagePath() {
        	return imagePath;
        }

	
        public void setImagePath(String imagePath) {
        	this.imagePath = imagePath;
        }

	
        public String getFilename() {
        	return filename;
        }

	
        public void setFilename(String filename) {
        	this.filename = filename;
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

	
        public User getCreator() {
        	return creator;
        }

	
        public void setCreator(User creator) {
        	this.creator = creator;
        }

	
        public Date getCreated() {
        	return created;
        }

	
        public void setCreated(Date created) {
        	this.created = created;
        }

	
        public User getModifer() {
        	return modifer;
        }

	
        public void setModifer(User modifer) {
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
        
        
}
