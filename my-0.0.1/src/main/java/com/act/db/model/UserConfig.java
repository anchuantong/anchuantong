
package com.act.db.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author an_chuantong
 */

@Entity
@Table(name = "user_config")
public class UserConfig implements Serializable {

	/**
         * 
         */
        private static final long serialVersionUID = 2274363812901689566L;

	@Id
	private String username;
	
	private String content;

	
        public String getContent() {
        	return content;
        }

	
        public void setContent(String content) {
        	this.content = content;
        }


	
        public String getUsername() {
        	return username;
        }


	
        public void setUsername(String username) {
        	this.username = username;
        }
	
	
}
