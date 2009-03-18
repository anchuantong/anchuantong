package com.act.bo;

import java.util.Map;

/**
 * 专题操作
 * @author anchuantong
 *
 */
public interface SpecialBo {

	/**
	 * 解析首页
	 * @param id
	 * @return
	 */
	public Map<String, Object>  renderHomepage(int id);
	
	
}
