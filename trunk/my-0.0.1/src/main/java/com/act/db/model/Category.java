package com.act.db.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OrderBy;
import org.hibernate.validator.NotNull;



@Entity
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Category implements Serializable {

	/**
         * 
         */
        private static final long serialVersionUID = 6617356720894719416L;

        @Id
        @GeneratedValue
        private Integer id;
        
        @NotNull
        private String name;
        
        private String path;
        
        private Integer level;
        
        private Integer pos;
        
        @ManyToOne
        @JoinColumn(name="parent")
        private Category parent;
        
       
        private String username;
        
        private Integer status;
        
        private Integer type;
        
        private String password;

        @OneToMany
        @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
        @JoinColumn(name = "parent", unique = false) 
        @OrderBy(clause = "pos asc")
        private List<Category> children;
        
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


	
        public String getPath() {
        	return path;
        }


	
        public void setPath(String path) {
        	this.path = path;
        }


	
        public Integer getLevel() {
        	return level;
        }


	
        public void setLevel(Integer level) {
        	this.level = level;
        }


	
        public Integer getPos() {
        	return pos;
        }


	
        public void setPos(Integer pos) {
        	this.pos = pos;
        }


	
        public Category getParent() {
        	return parent;
        }


	
        public void setParent(Category parent) {
        	this.parent = parent;
        }


	
       


	
        public Integer getStatus() {
        	return status;
        }


	
        public void setStatus(Integer status) {
        	this.status = status;
        }


	
        public Integer getType() {
        	return type;
        }


	
        public void setType(Integer type) {
        	this.type = type;
        }


	
        public List<Category> getChildren() {
        	return children;
        }


	
        public void setChildren(List<Category> children) {
        	this.children = children;
        }


	
        public String getPassword() {
        	return password;
        }


	
        public void setPassword(String password) {
        	this.password = password;
        }

        public String getRecursiveIds() {
		if (children != null && children.size() > 0) {
			String ids = "'" + id;
			for (int i = 0; i < children.size(); i++) {
				if (!ids.endsWith("'")) {
					ids = ids + "'";
				}
				ids = ids + "," + ((Category) children.get(i)).getRecursiveIds();
			}
			return ids;
		} else {
			return "'" + id + "'";
		}
	}
        
        public Category[] getParents() {
		if (this.parent == null) {
			return null;
		}
		Category[] parents = new Category[this.level - 1];
		Category tmp = this.getParent();
		for (int i = parents.length - 1; i >= 0; i--) {
			parents[i] = tmp;
			tmp = tmp.getParent();
		}
		return parents;
	}
       
        public Category getRoot() {
        	Category[] parents=getParents();
        	if(parents!=null&&parents.length>0){
        		return parents[0];
        	}else{
        		return this;
        	}
        }


	
        public String getUsername() {
        	return username;
        }


	
        public void setUsername(String username) {
        	this.username = username;
        }
        
        
}
