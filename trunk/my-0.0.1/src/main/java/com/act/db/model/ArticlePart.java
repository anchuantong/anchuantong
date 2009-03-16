package com.act.db.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author  an_chuantong
 * 
 */

@Entity
@Table(name="article_part")
public class ArticlePart  implements Serializable {

	/**
         * 
         */
        private static final long serialVersionUID = 6617356720894719416L;

        @Id
        @GeneratedValue
        private Integer id;
        
        @ManyToOne
        @JoinColumn(name="article",nullable=false,insertable=false,updatable=false)
        private Article article;
        
        private String body;

        private String title;
        
        public Integer getId() {
        	return id;
        }

	
        public void setId(Integer id) {
        	this.id = id;
        }

	
        public Article getArticle() {
        	return article;
        }

	
        public void setArticle(Article article) {
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
