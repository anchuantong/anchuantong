
package com.act.bo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.suigeneris.jrcs.diff.DiffException;
import org.suigeneris.jrcs.rcs.Archive;
import org.suigeneris.jrcs.rcs.InvalidFileFormatException;
import org.suigeneris.jrcs.rcs.InvalidVersionNumberException;
import org.suigeneris.jrcs.rcs.impl.NodeNotFoundException;
import org.suigeneris.jrcs.util.ToString;

import com.act.bean.ArchiveBean;
import com.act.bo.ArticleBo;
import com.act.db.model.Article;
import com.act.db.model.ArticlePart;
import com.act.db.model.Category;
import com.act.util.PageResult;
import com.act.util.StringUtil;

/**
 * @author an_chuantong
 */

public class ArticleBoImpl extends BaseBoImpl implements ArticleBo {

	public PageResult search(Map<String, Object> params, int pge, int limit) {
		StringBuffer sql = new StringBuffer(" from Article where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		PageResult pageResult = new PageResult();

		int total = getDao().count("select count(id) " + sql.toString(), values);
		pageResult.setParameters(total, pge, limit);
		pageResult.setResult(getDao().findList(sql.toString(), values, pageResult.getStart(), pageResult.getLimit()));
		return pageResult;
	}

	public boolean saveArticle(Article article, String body,String partTitle, int part) {
		article.setDescription(StringUtil.summarize(StringUtil.html2txt(body), 150));
		getDao().save(article);
		List<ArticlePart> parts = article.getParts();
		ArticlePart articlePart = null;
		if (parts == null) {
			parts = new ArrayList<ArticlePart>();
			article.setParts(parts);
		}
		if (parts.size() > part) {
			articlePart = article.getParts().get(part);
		} else {
			articlePart = new ArticlePart();
			articlePart.setArticle(article);
			article.getParts().add(articlePart);
		}
		Archive archive=null;
		if(StringUtil.isEmpty(articlePart.getBody())){
			 archive= new Archive(ToString.stringToArray(body), "");
		}else{
			archive=ArchiveBean.setArchive(articlePart.getBody());
			try {
	                        archive.addRevision(ToString.stringToArray(body), "");
                        } catch (InvalidVersionNumberException e) {
                        } catch (NodeNotFoundException e) {
                        } catch (InvalidFileFormatException e) {
                        } catch (DiffException e) {
                        }
		}
		
		articlePart.setBody(archive.toString());
		articlePart.setTitle(partTitle);
		getDao().save(articlePart);
		getDao().update(article);
		return true;
	}
	
	public boolean saveArticleSmall(Article article){
		getDao().save(article);
		return true;
	}

	public Article loadArticle(Integer id) {
		// TODO Auto-generated method stub
		return (Article) getDao().get(Article.class, id);
	}

	public Integer countArticleByCat(Category cat, boolean approved) {
		String sql = "select count(*) from Article where category IN ( " + cat.getRecursiveIds() + " ) ";
		if (approved) {
			sql += " and published=1";
		}
		return getDao().count(sql);
	}

	@SuppressWarnings("unchecked")
        public List<Article> findArticleByCat(Category cat, boolean approved) {
		String sql = "from Article where category IN ( " + cat.getRecursiveIds() + " ) ";
		if (approved) {
			sql += " and published=1";
		}
		return getDao().findList(sql);
	}

	public void deleteArticle(Article article) {
		getDao().delete(article);
	}

	public void addPart(Article article) {
		List<ArticlePart> parts = article.getParts();
		ArticlePart articlePart = null;
		if (parts == null) {
			parts = new ArrayList<ArticlePart>();
			article.setParts(parts);
		}
		articlePart = new ArticlePart();
		articlePart.setArticle(article);
		article.getParts().add(articlePart);
		articlePart.setBody("");
		getDao().save(articlePart);
		getDao().update(article);
	}

	public void deletePart(Article article, int part) {
		if (article.getParts() != null && article.getParts().size() != 0) {
			ArticlePart articlePart = (ArticlePart) article.getParts()
					.get(part);
			if (article.getParts().size() == 1) {
				articlePart.setBody("");
				getDao().update(articlePart);
			} else {
				article.getParts().remove(part);
				getDao().delete(articlePart);
			}
		}
		
	}
}
