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
@Table(name="special_article_part")

public class SpecialArticlePart implements Serializable {

	/**
         * 
         */
        private static final long serialVersionUID = 3112817818897500613L;

        @Id
        @GeneratedValue
        private Integer id;
        
        @ManyToOne
        @JoinColumn(name="article",nullable=false,insertable=false,updatable=false)
        private SpecialArticle article;
        
        private String body;

        private String title;
        
        public Integer getId() {
        	return id;
        }

	
        public void setId(Integer id) {
        	this.id = id;
        }

	
        public SpecialArticle getArticle() {
        	return article;
        }

	
        public void setArticle(SpecialArticle article) {
        	this.article = article;
        }

	
        public String getBody() {
        	return body;
        }

	
        public void setBody(String body) {
        	this.body = body;
        }


	
        public String getTitle() {
        	return title;
        }


	
        public void setTitle(String title) {
        	this.title = title;
        }

}
