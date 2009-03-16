
package com.act.bean;

import java.io.File;

import org.springframework.core.io.Resource;

public class ConstantBean {

	private File basePath;

	private File uploadPath;
	

	public File getBasePath() {
		return basePath;
	}

	public File getUploadPath() {
		return uploadPath;
	}
	
	public void setBaseLocation(Resource resource) throws java.io.IOException {
		this.basePath = resource.getFile();
	}

	
	public void setUploadLocation(Resource resource) throws java.io.IOException {
		this.uploadPath = resource.getFile();
	}

}
