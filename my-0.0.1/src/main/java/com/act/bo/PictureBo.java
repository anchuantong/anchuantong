
package com.act.bo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.act.db.model.Category;
import com.act.db.model.Picture;
import com.act.db.model.User;

/**
 * @author an_chuantong
 */

public interface PictureBo {

	public List<Picture> findPictureByCat(Category category,int start,int limit);
	
	public boolean addPicture(Picture picture,User user,MultipartFile upload) ;
}
