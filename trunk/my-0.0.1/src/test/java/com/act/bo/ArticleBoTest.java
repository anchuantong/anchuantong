package com.act.bo;

import java.util.Date;

import com.act.db.model.Article;
import com.act.db.model.Category;
import com.act.db.model.User;
import com.act.spring.BaseTest;
import com.act.util.PageResult;


/**
 * @author  an_chuantong
 * 
 */

public class ArticleBoTest extends BaseTest {
	private CategoryBo categoryBo;
	private UserBo userBo;
	private ArticleBo articleBo;
	public void setUp(){
		super.setUp();
		categoryBo=(CategoryBo) context.getBean("categoryBo");
		userBo=(UserBo) context.getBean("userBo");
		articleBo=(ArticleBo) context.getBean("articleBo");
	}
	
	public void testSaveArticle(){
		Category category=categoryBo.loadCategory(1);
		Article article=new Article();
		article.setCategory(category);
		User user=userBo.findUser("anct125");
		article.setCreated(new Date());
		article.setCreator(user.getUsername());
		article.setHits(0);
		article.setModifed(new Date());
		article.setModifer(user.getUsername());
		article.setTitle("tttttttttttt");
		articleBo.saveArticle(article, "fdsfdsdsfdsds", "",0);
		System.out.println(user);
	}
	
	public void testSearch(){
		PageResult pageResult=articleBo.search(null, 1, 6);
		System.out.println(pageResult.getResult());
	}
	
	
}
