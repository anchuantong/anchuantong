package com.act.bo;

import java.util.List;
import java.util.Map;

import com.act.db.model.Article;
import com.act.db.model.Category;
import com.act.util.PageResult;

/**
 * @author  an_chuantong
 * 
 */

public interface ArticleBo {

	/**
	 * ��ѯ�����б�
	 * @param params
	 * @param pge
	 * @param limit
	 * @return
	 */
	public PageResult search(Map<String, Object> params,int pge,int limit);
	
	/**
	 * �����޸�����
	 * @param article
	 * @param body
	 * @param part
	 * @return
	 */
	public boolean saveArticle(Article article,String body,String partTitle,int part);
	
	public boolean saveArticleSmall(Article article);
	
	public void deleteArticle(Article article);

	public Article loadArticle(Integer id);
	
	/**
	 * ͳ��ĳ�����������б�
	 * approved����trueֻͳ���ѷ���
	 * @param cat
	 * @param approved
	 * @return
	 */
	public Integer countArticleByCat(Category cat,boolean approved);
	
	public List<Article> findArticleByCat(Category cat,boolean approved);

	public void addPart(Article article);
	
	public void deletePart(Article article,int part);

	
}
