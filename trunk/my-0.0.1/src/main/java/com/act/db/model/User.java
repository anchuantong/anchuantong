
package com.act.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.validator.Email;

import com.act.web.util.UserSession;

@Entity
@Table(name = "user")
public class User implements Serializable,UserSession {

	/**
         * 
         */
        private static final long serialVersionUID = -578790651520778L;

        @Id
        @GeneratedValue
        private Integer id;
        
        @Index(name="username")
        private String username;
        
        private String password;
        
        @Email
        private String email;

        @Column
        private Date created;
        
        public Integer getId() {
        	return id;
        }

	
        public void setId(Integer id) {
        	this.id = id;
        }


	
        public String getUsername() {
        	return username;
        }


	
        public void setUsername(String username) {
        	this.username = username;
        }


	
        public String getPassword() {
        	return password;
        }


	
        public void setPassword(String password) {
        	this.password = password;
        }


	
        public Date getCreated() {
        	return created;
        }


	
        public void setCreated(Date created) {
        	this.created = created;
        }


	
        public String getEmail() {
        	return email;
        }


	
        public void setEmail(String email) {
        	this.email = email;
        }
}
