package com.act.bo.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.act.db.dao.HibernateDao;

/**
 * @author  an_chuantong
 * 
 */

public class BaseBoImpl {

	protected Log log = LogFactory.getLog("sys.bo");
	
	private HibernateDao dao;
	
        public HibernateDao getDao() {
        	return dao;
        }

	@Autowired
        public void setDao(@Qualifier("hibernateDao")HibernateDao dao) {
        	this.dao = dao;
        }
}
