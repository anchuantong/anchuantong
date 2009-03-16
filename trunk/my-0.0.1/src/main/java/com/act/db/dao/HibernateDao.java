package com.act.db.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * @author  an_chuantong
 * 
 */

public interface HibernateDao  {

	public TransactionTemplate getTransactionTemplate();

	public Object findOne(String sql);

	public Object get(Class entityClass, Serializable id) ;
	

	public Object findOne(String sql, Object arg) ;

	public Object findOne(final String sql, final Object[] values) ;

	public List findList(final String sql, final List values, final List types, final int first, final int limit);

	public List findList(final String sql, final Object[] values, final int first, final int limit) ;

	public List findList(final String sql, final Object[] values, final int first, final int limit, final boolean cache) ;

	public List findList(final String sql, final int first, final int limit) ;

	public List findList(final String sql, final List values, final int first, final int limit) ;

	public List findList(final String sql, final Object[] values) ;
	
	public List findList(final String sql, final Object[] values, boolean cache) ;

	public List findList(final String sql) ;
	
	public List findList(final String sql, boolean cache);

	public int count(String sql, List values, List types) ;

	public int count(final String sql, final Object[] values, final Type[] types) ;

	public int count(final String sql, final List values) ;

	public int count(final String sql, final Object[] values) ;

	public int count(final String sql, final Object value, final Type type) ;

	public int count(final String sql) ;
	
	public void save(Object obj);

	public void flush();

	public void update(Object obj);

	public void saveOrUpdate(Object obj);

	public void delete(Object obj);

	@SuppressWarnings("unchecked")
	public void evict(Class classz, Serializable id);

	@SuppressWarnings("unchecked")
	public void evict(Class classz);
	
	public void evictCollection(String roleName);

	public void evictCollection(String roleName, Serializable id);

	public void evictQueries();

	public void evictQueries(String region);

	public void debug();




}
