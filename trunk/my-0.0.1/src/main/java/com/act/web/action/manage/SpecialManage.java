
package com.act.web.action.manage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.act.bean.ConstantBean;
import com.act.bo.SpecialBo;
import com.act.db.model.Special;
import com.act.db.model.SpecialArticle;
import com.act.db.model.User;
import com.act.enums.SpecialTypeEnum;
import com.act.util.ArrayUtil;
import com.act.util.FileUtil;
import com.act.util.ImageUtil;
import com.act.util.PageBuilder;
import com.act.util.PageResult;

/**
 * ×¨Ìâ¿ØÖÆ
 * 
 * @author anchuantong.tw
 */
@Controller
@RequestMapping("/special/manage/*")
@SessionAttributes("userSession")
public class SpecialManage extends BaseController {

	private SpecialBo specialBo;

	private ConstantBean constant;

	@Autowired
	public void setConstant(ConstantBean constant) {
		this.constant = constant;
	}

	@Autowired
	public void setSpecialBo(SpecialBo specialBo) {
		this.specialBo = specialBo;
	}

	@ModelAttribute("layout")
	public String setLayout() {
		return "layout/none.vm";
	}

	@ModelAttribute("types")
	public SpecialTypeEnum[] setTypes() {
		return SpecialTypeEnum.values();
	}

	@RequestMapping
	public String index(@ModelAttribute("userSession") User user, ModelMap model) {
		PageResult pageResult = specialBo.search(null, 1, 20);
		model.addAttribute("pb", pageResult.getPageBuilder());
		model.addAttribute("list", pageResult.getResult());
		return "special/manage/index";
	}

	@RequestMapping
	public String show(Special specialForm, ModelMap model) {
		if (specialForm != null && specialForm.getId() != null) {
			model.addAttribute("special", specialBo.loadSpecial(specialForm.getId()));
		}
		return "special/manage/show";
	}

	@RequestMapping
	public String edit(Special specialForm, @ModelAttribute("userSession") User user, ModelMap model) {
		specialForm = specialBo.edit(specialForm, user.getUsername());
		return show(specialForm, model);
	}

	@RequestMapping
	public String listArticle(SpecialArticle specialArticleForm, @RequestParam(value = "specialId", required = false) Integer specialId, @RequestParam(value = "pge", required = false) Integer pge, ModelMap model) {
		model.addAttribute("special", specialBo.loadSpecial(specialId));
		PageBuilder pb = new PageBuilder(25);
		int total = specialBo.countArticle(specialId, Boolean.FALSE);
		pb.items(total);
		if (pge == null || pge <= 0) {
			pge = 1;
		}
		pb.page(pge);
		model.addAttribute("pb", pb);
		model.addAttribute("list", specialBo.findArticle(specialId, Boolean.FALSE, pb.offset(), pb.length()));
		return "special/manage/listArticle";
	}

	@RequestMapping
	public String showArticle(SpecialArticle specialArticleForm, int specialId, @RequestParam(value = "part", required = false) Integer part, ModelMap model) {
		if (part == null) {
			part = 0;
		}
		if (specialArticleForm != null && specialArticleForm.getId() != null) {
			SpecialArticle article = specialBo.loadArticle(specialArticleForm.getId());
			model.addAttribute("article", article);
		}
		model.addAttribute("special", specialBo.loadSpecial(specialId));
		model.addAttribute("part", part);
		return "special/manage/showArticle";
	}

	@RequestMapping
	public String editArticle(SpecialArticle specialArticleForm, int specialId, int part, @RequestParam(value = "upload", required = false) MultipartFile upload, @ModelAttribute("userSession") User user, ModelMap model) throws IOException {
		log.debug("upload:" + upload.getSize());
		if (upload.getSize() > 0) {
			InputStream in = upload.getInputStream();
			String extension = FileUtil.getExtension(upload.getOriginalFilename());
			if (ArrayUtil.contains(ImageUtil.EXTENSIONS, extension)) {
				String filename = FileUtil.getUniqueFileName() + "." + extension;
				File file = new File(constant.getUploadPath(), filename);
				FileUtil.copy(in, file);
				
			}
			log.debug("extension:" + upload.getOriginalFilename());
		}
		Special special = specialBo.loadSpecial(specialId);
		specialArticleForm = specialBo.editArticle(specialArticleForm, special, part, user.getUsername());
		model.addAttribute("special", special);
		model.addAttribute("specialArticle", specialArticleForm);
		return "redirect:showArticle?id=" + specialArticleForm.getId() + "&part=" + part + "&specialId=" + specialId;
	}

	@RequestMapping
	public String deleteArticle(@RequestParam("id") Integer id, int specialId, @ModelAttribute("userSession") User user) {
		SpecialArticle article = specialBo.loadArticle(id);
		System.out.println(article);
		if (article != null) {
			specialBo.deleteArticle(article);
			return "success";
		}
		return "redirect:index";
	}

	@RequestMapping
	public String addPart(@RequestParam("id") Integer id, int specialId, @ModelAttribute("userSession") User user) {
		SpecialArticle article = specialBo.loadArticle(id);
		if (article != null && article.getCreator().equals(user.getUsername())) {
			specialBo.addPart(article);

		}
		int next = article.getParts().size() - 1;
		if (next < 0) {
			next = 0;
		}
		return "redirect:showArticle?id=" + article.getId() + "&part=" + next + "&specialId=" + specialId;
	}

	@RequestMapping
	public String deletePart(@RequestParam("id") Integer id, @RequestParam("part") Integer part, int specialId, @ModelAttribute("userSession") User user) {
		SpecialArticle article = specialBo.loadArticle(id);

		if (article != null && article.getCreator().equals(user.getUsername())) {
			specialBo.deletePart(article, part);
		}
		int prev = article.getParts().size() - 1;
		if (prev < 0) {
			prev = 0;
		}
		return "redirect:showArticle?id=" + article.getId() + "&part=" + prev;
	}
}
