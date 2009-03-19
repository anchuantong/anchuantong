
package com.act.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.act.db.model.Special;
import com.act.db.model.SpecialArticle;
import com.act.db.model.SpecialArticlePart;
import com.act.util.PageResult;

/**
 * 专题操作
 * 
 * @author anchuantong
 */
public interface SpecialBo {

	public Special loadSpecial(int id);

	public SpecialArticle loadArticle(Integer id);

	/**
	 * 解析首页
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> renderHomepage(int id);

	/**
	 * 查询专题
	 * 
	 * @param params
	 * @param pge
	 * @param limit
	 * @return
	 */
	public PageResult search(Map<String, Object> params, int pge, int limit);

	/**
	 * @param specialForm
	 * @param username
	 * @return
	 */
	public Special edit(Special specialForm, String username);

	public int countArticle(int specialId, boolean approved);
	
	public List<SpecialArticle> findArticle(int specialId, boolean approved,int start,int limit);
	
	public SpecialArticle editArticle(SpecialArticle specialArticleForm, Special special, int part, String username);

	
	public void deleteArticle(SpecialArticle article);

	public void addPart(SpecialArticle article);

	public void deletePart(SpecialArticle article, int part);

}
