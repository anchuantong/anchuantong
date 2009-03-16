
package com.act.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.act.bo.ArticleBo;
import com.act.bo.UserBo;
import com.act.db.model.Article;
import com.act.db.model.Category;
import com.act.db.model.User;
import com.act.form.ArticleForm;
import com.act.util.PageBuilder;
import com.act.util.PageResult;

/**
 * @description »ùÓÚspring 2.5µÄMVC
 * @author an_chuantong
 */

@Controller
@RequestMapping("/user/article/*")
@SessionAttributes("userSession")
public class ArticleManage extends BaseController {

	private UserBo userBo;

	private ArticleBo articleBo;

	@Autowired
	public void setArticleBo(ArticleBo articleBo) {
		this.articleBo = articleBo;
	}

	@Autowired
	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	@RequestMapping
	public String index(@ModelAttribute("userSession") User user, ModelMap model) {
		PageResult pageResult = articleBo.search(null, 1, 20);
		model.addAttribute("pb", pageResult.getPageBuilder());
		model.addAttribute("list", pageResult.getResult());
		return "user/article/index";
	}

	@RequestMapping
	public String indexCat(Category category, @ModelAttribute("userSession") User user, ModelMap model) {
		category = categoryBo.loadCategory(category.getId());
		PageBuilder pb = new PageBuilder(25);
		int total = articleBo.countArticleByCat(category, Boolean.FALSE);
		pb.items(total);
		pb.page(1);
		model.addAttribute("pb", pb);
		model.addAttribute("list", articleBo.findArticleByCat(category, Boolean.FALSE));
		return "user/article/index_category";
	}

	@RequestMapping
	public String showEdit(ArticleForm form, ModelMap model, @ModelAttribute("userSession") User user) {
		model.addAttribute("categories", categoryBo.loadRootCategories(user.getUsername()));
		if (form != null && form.getId() != null && form.getId() > 0) {
			model.addAttribute("article", articleBo.loadArticle(form.getId()));
		}
		model.addAttribute("part", form.getPart());
		return "user/article/edit";
	}

	@RequestMapping
	public String edit(Article article,ArticleForm form , ModelMap model, @ModelAttribute("userSession") User user) {
		Article articlePo = null;
		if (article != null && article.getId() != null && article.getId() > 0) {
			articlePo = articleBo.loadArticle(article.getId());
		}
		if (articlePo == null) {
			articlePo = article;
			articlePo.setCreated(new Date());
			articlePo.setCreator(user.getUsername());
			articlePo.setHits(0);
		} else {
			articlePo.setTitle(article.getTitle());
		}
		Integer catId=form.getCatId();
		if (catId != null && catId > 0) {
			Category category = categoryBo.loadCategory(catId);
			if (category != null) {
				articlePo.setCategory(category);
			}
		}
		user = userBo.findUser(user.getUsername());
		articlePo.setModifed(new Date());
		articlePo.setModifer(user.getUsername());
		boolean succ = articleBo.saveArticle(articlePo, form.getBody(), form.getPartTitle(),form.getPart());
		
		if (succ) {
			return "success";
		}
		
		return "user/article/edit";
	}

	@RequestMapping
	public String delete(@RequestParam("id") Integer id, @ModelAttribute("userSession") User user) {
		Article article = articleBo.loadArticle(id);
		if (article != null && article.getCreator().equals(user.getUsername())) {
			articleBo.deleteArticle(article);
			return "success";
		}
		return "redirect:index";
	}
	
	
	@RequestMapping
	public String addPart(@RequestParam("id") Integer id, @ModelAttribute("userSession") User user) {
		Article article = articleBo.loadArticle(id);
		if (article != null && article.getCreator().equals(user.getUsername())) {
			articleBo.addPart(article);
			
		}
		int next = article.getParts().size() - 1;
		if (next < 0) {
			next = 0;
		}
		return "redirect:showEdit?id="+article.getId()+"&part="+next;
	}
	
	@RequestMapping
	public String deletePart(@RequestParam("id") Integer id,@RequestParam("part") Integer part, @ModelAttribute("userSession") User user) {
		Article article = articleBo.loadArticle(id);
		
		if (article != null && article.getCreator().equals(user.getUsername())) {
			articleBo.deletePart(article,part);
		}
		int prev = article.getParts().size() - 1;
		if (prev < 0) {
			prev = 0;
		}
		return "redirect:showEdit?id="+article.getId()+"&part="+prev;
	}

}
