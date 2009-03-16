
package com.act.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.act.bo.ArticleBo;
import com.act.db.model.Article;
import com.act.db.model.ArticlePart;
import com.act.db.model.Category;
import com.act.util.PageBuilder;
import com.act.util.StringUtil;

/**
 * @author an_chuantong
 */

@Controller
public class IndexPage extends BaseController {

	private ArticleBo articleBo;

	@Autowired
	public void setArticleBo(ArticleBo articleBo) {
		this.articleBo = articleBo;
	}

	@RequestMapping("/index")
	public String index() {
		return "page/index";
	}

	@RequestMapping("/article/index/*")
	public String articleIndex(HttpServletRequest request,ModelMap model) {
		String ids[] = StringUtil.parse(request.getRequestURI(), 1);
		int pge = 1;
		Integer id = 0;
		int pos = ids[0].indexOf("_");
		if (pos > -1) {
			pge = StringUtil.asInteger(ids[0].substring(0, pos), 0);
			id = StringUtil.asInteger(ids[0].substring(pos + 1), 0);
		} else {
			id = StringUtil.asInteger(ids[0], 0);
		}
		Category category = categoryBo.loadCategory(id);
		if (category == null) {
			return "redirect:404";
		}
		PageBuilder pb = new PageBuilder(25);
		int total = articleBo.countArticleByCat(category, Boolean.FALSE);
		pb.items(total);
		pb.page(pge);
		model.addAttribute("pb", pb);
		model.addAttribute("category", category);
		model.addAttribute("list", articleBo.findArticleByCat(category, Boolean.FALSE));
		return "page/index_article";
	}

	
        @RequestMapping("/article/detail/*")
	public String articleDetail(HttpServletRequest request,ModelMap model) {
		String ids[] = StringUtil.parse(request.getRequestURI(), 1);
		int part = 0;
		Integer id = 0;
		int pos = ids[0].indexOf("_");
		if (pos > -1) {
			part = StringUtil.asInteger(ids[0].substring(0, pos), 0);
			id = StringUtil.asInteger(ids[0].substring(pos + 1), 0);
		} else {
			id = StringUtil.asInteger(ids[0], 0);
		}
		Article article=articleBo.loadArticle(id);
		
		if(article==null){
			return "redirect:404";
		}
		
		List<ArticlePart> parts=article.getParts();
		if(parts==null||parts.size()<part){
			return "redirect:404";
		}
		
		ArticlePart articlePart=article.getParts().get(part);
		model.addAttribute("article",article );
		model.addAttribute("body",articlePart.getBody());
		model.addAttribute("part",part);
		if(parts.size()>1){
			model.addAttribute("parts",parts);
		}
		model.addAttribute("category",article.getCategory());
		return "page/index_detail";
	}

}
