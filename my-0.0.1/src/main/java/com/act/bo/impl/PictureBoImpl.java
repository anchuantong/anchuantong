
package com.act.bo.impl;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.act.bean.ConstantBean;
import com.act.bo.PictureBo;
import com.act.db.model.Category;
import com.act.db.model.Picture;
import com.act.db.model.User;
import com.act.util.ArrayUtil;
import com.act.util.FileUtil;
import com.act.util.ImageUtil;

/**
 * @author an_chuantong
 */

public class PictureBoImpl extends BaseBoImpl implements PictureBo {

	private final static int maxWidth = 640;

	private ConstantBean constant;
	
	@Autowired
        public void setConstant(ConstantBean constant) {
        	this.constant = constant;
        }
	
	public List<Picture> findPictureByCat(Category category, int start, int limit) {
		return getDao().findList("from Picture where category=? order by created desc", new Object[] { category }, start, limit);

	}

	public boolean addPicture(Picture picture, User user, MultipartFile upload) {
		picture.setCreated(new Date());
		picture.setCreator(user);
		picture.setModifed(new Date());
		picture.setModifer(user);
		String filename = FileUtil.getFileName(upload.getName());
		String extension = FileUtil.getExtension(filename);
		if (ArrayUtil.contains(FileUtil.IMAGE_TYPES, extension.toLowerCase())) {
			String path = FileUtil.getUniqueFileName() + "." + extension;
			File file = new File(constant.getUploadPath(),path);
			file.getParentFile().mkdirs();
			picture.setSize(Integer.parseInt(upload.getSize()+""));
			try {
				FileUtil.copy(upload.getInputStream(), file);
				Dimension d = ImageUtil.getDimension(upload.getInputStream());
				int width = (int) d.getWidth();
				int height = (int) d.getHeight();
				if (maxWidth > 0 && width > maxWidth) {
					height = ((int) (d.getHeight() * maxWidth) / width);
					width = maxWidth;
					ImageUtil.cropAndScaleImage(file, file, width, height);
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}

		}
		
		return true;
	}

	
}
