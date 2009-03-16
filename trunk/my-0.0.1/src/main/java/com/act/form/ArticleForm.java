package com.act.form;

/**
 * @author  an_chuantong
 * 
 */

public class ArticleForm {

	private Integer id;
	
	private Integer catId;
	
	private String cmd;
	
	private Integer parentId;
	
	private Integer part=0;

	private String partTitle;
	
	private String body;
	
        public Integer getId() {
        	return id;
        }

	
        public void setId(Integer id) {
        	this.id = id;
        }

	
        public Integer getCatId() {
        	return catId;
        }

	
        public void setCatId(Integer catId) {
        	this.catId = catId;
        }

	
        public String getCmd() {
        	return cmd;
        }

	
        public void setCmd(String cmd) {
        	this.cmd = cmd;
        }

	
        public Integer getParentId() {
        	return parentId;
        }

	
        public void setParentId(Integer parentId) {
        	this.parentId = parentId;
        }

	
        public Integer getPart() {
        	return part;
        }

	
        public void setPart(Integer part) {
        	this.part = part;
        }


	
        public String getPartTitle() {
        	return partTitle;
        }


	
        public void setPartTitle(String partTitle) {
        	this.partTitle = partTitle;
        }


	
        public String getBody() {
        	return body;
        }


	
        public void setBody(String body) {
        	this.body = body;
        }
}
