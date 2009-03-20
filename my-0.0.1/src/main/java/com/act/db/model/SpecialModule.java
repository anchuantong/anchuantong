package com.act.db.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OrderBy;

@Entity
@Table(name = "special_module")
public class SpecialModule implements Serializable {
        
	/**
         * 
         */
        private static final long serialVersionUID = 3014472890390109522L;
        
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private Integer pos;
	
	private String block;
	
	@ManyToOne
	@JoinColumn(name="special")
	private Special special;

	@OneToMany
	@JoinColumn(name = "module", unique = false)
	@OrderBy(clause = "pos asc")
	private List<SpecialModuleList> list;
	
	@OneToMany
	@JoinColumn(name = "module", unique = false)
	private List<SpecialModuleBody> bodys;
	
        private Integer type;

        @ManyToOne
	@JoinColumn(name="parent")
        private SpecialModule parent;
        
        @OneToMany
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

	
        public Integer getPos() {
        	return pos;
        }

	
        public void setPos(Integer pos) {
        	this.pos = pos;
        }

	
        public String getBlock() {
        	return block;
        }

	
        public void setBlock(String block) {
        	this.block = block;
        }

	
        public Special getSpecial() {
        	return special;
        }

	
        public void setSpecial(Special special) {
        	this.special = special;
        }
        
        public List<SpecialModuleList> getList() {
        	return list;
        }


	
        public void setList(List<SpecialModuleList> list) {
        	this.list = list;
        }


	
        public List<SpecialModuleBody> getBodys() {
        	return bodys;
        }

        public void setBodys(List<SpecialModuleBody> bodys) {
        	this.bodys = bodys;
        }


        public Integer getType() {
        	return type;
        }

        public void setType(Integer type) {
        	this.type = type;
        }


	
        public SpecialModule getParent() {
        	return parent;
        }


	
        public void setParent(SpecialModule parent) {
        	this.parent = parent;
        }


	
        public List<Category> getChildren() {
        	return children;
        }


	
        public void setChildren(List<Category> children) {
        	this.children = children;
        }
}
