
package com.act.web.action.manage;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.act.bo.PictureBo;
import com.act.db.model.User;

/**
 * @author an_chuantong
 */

@Controller
@RequestMapping("/user/album/*")
@SessionAttributes("userSession")
public class AlbumManage extends BaseController {


	private PictureBo pictureBo;

	public void setPictureBo(PictureBo pictureBo) {
		this.pictureBo = pictureBo;
	}

	@RequestMapping
	public String showUpload(ModelMap model,@ModelAttribute("userSession") User user) {
		model.addAttribute("category",categoryBo.loadPicCategory(user.getUsername()));
		return "user/picture/upload";
	}
	
	@RequestMapping
	public String upload(@RequestParam("upload") MultipartFile upload) throws IOException {
		InputStream in=upload.getInputStream();
		return "user/picture/upload";
	}
}
