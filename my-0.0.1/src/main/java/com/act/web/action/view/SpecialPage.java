package com.act.web.action.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.act.bo.SpecialBo;
import com.act.util.StringUtil;

/**
 * ×¨Ìâ
 * @author anchuantong
 *
 */
@Controller
public class SpecialPage {
	private SpecialBo specialBo;

	@Autowired
	public void setArticleBo(SpecialBo specialBo) {
		this.specialBo = specialBo;
	}
	

	@RequestMapping("/special/*/index.html")
	public String index(HttpServletRequest request,ModelMap model) {
		String ids[] = StringUtil.parse(request.getRequestURI(), 2);
		int id=StringUtil.asInteger(ids[0], 0);
		if(id==0){
			return "redirect:404";
		}
		
		Map<String, Object> content=specialBo.renderHomepage(id);
		if(content==null){
			return "redirect:404";
		}
		model.addAllAttributes(content);
		return "page/special/homepage";
	}

}
