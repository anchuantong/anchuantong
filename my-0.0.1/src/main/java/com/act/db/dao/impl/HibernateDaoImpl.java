package com.act.db.dao.impl;

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
import com.act.db.dao.HibernateDao;

/**
 * @author  an_chuantong
 * 
 */

public class HibernateDaoImpl extends HibernateDaoSupport implements HibernateDao {

	private PlatformTransactionManager transactionManager;
	

	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public TransactionTemplate getTransactionTemplate() {
		return new TransactionTemplate(this.transactionManager);
	}

	protected Log log = LogFactory.getLog("sys.dao");

	public Object findOne(String sql) {
		return findOne(sql, null);
	}


	public Object get(Class entityClass, Serializable id) {
		if (id instanceof Integer && ((Integer) id).intValue() <= 0) {
			return null;
		} else if (id instanceof Long && ((Long) id).intValue() <= 0) {
			return null;
		}
		return this.getHibernateTemplate().get(entityClass, id);
	}

	public Object findOne(String sql, Object arg) {
		return findOne(sql, new Object[] { arg });
	}

	/**
	 * if count * returns long, we still convert it to integer
	 * 
	 * @param obj
	 * @return
	 */
	public int getCountValue(Object obj) {
		if (obj instanceof Integer) {
			return (Integer) obj;
		} else if (obj instanceof Long) {
			return ((Long) obj).intValue();
		}
		throw new IllegalArgumentException(obj + " is not a number for count value");
	}

	public Object findOne(final String sql, final Object[] values) {
		// List list = this.getHibernateTemplate().find(sql,args);
		List list = this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				query.setMaxResults(1);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.list();
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List findList(final String sql, final List values, final List types, final int first, final int limit) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.size(); i++) {
						query.setParameter(i, values.get(i), (Type) types.get(i));
					}
				}
				if (first > 0) {
					query.setFirstResult(first);
				}
				if (limit > 0) {
					query.setMaxResults(limit);
				}
				return query.list();
			}
		});
	}

	public List findList(final String sql, final Object[] values, final int first, final int limit) {
		return findList(sql, values, first, limit, true);
	}

	public List findList(final String sql, final Object[] values, final int first, final int limit, final boolean cache) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				if (first > 0) {
					query.setFirstResult(first);
				}
				if (limit > 0) {
					query.setMaxResults(limit);
				}
				if (!cache) {
					query.setCacheable(false);
				}
				return query.list();
			}
		});
	}

	public List findList(final String sql, final int first, final int limit) {
		Object[] obj = null;
		return this.findList(sql, obj, first, limit);
	}

	public List findList(final String sql, final List values, final int first, final int limit) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.size(); i++) {
						query.setParameter(i, values.get(i));
					}
				}
				if (first > 0) {
					query.setFirstResult(first);
				}
				if (limit > 0) {
					query.setMaxResults(limit);
				}
				//System.out.println("sql:"+query.getQueryString());
				return query.list();
			}
		});
	}

	public List findList(final String sql, final Object[] values) {
		return findList(sql, values,Boolean.FALSE);
	}
	
	public List findList(final String sql, final Object[] values,final boolean cache) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				if(cache){
					query.setCacheable(cache);
				}
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				query.setCacheable(true);
				return query.list();
			}
		});
	}

	public List findList(final String sql) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				return query.list();
			}
		});
	}
	
	public List findList(final String sql,final boolean cache) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				query.setCacheable(cache);
				return query.list();
			}
		});
	}

	public int count(String sql, List values, List types) {
		return count(sql, (Object[]) values.toArray(new Object[values.size()]), (Type[]) types.toArray(new Type[types.size()]));
	}

	public int count(final String sql, final Object[] values, final Type[] types) {
		return getCountValue(this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i], types[i]);
					}
				}
				return query.iterate().next();
				// return
				// session.iterate(sql,values,types).next();
			}
		}));
	}

	public int count(final String sql, final List values) {
		return getCountValue(this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.size(); i++) {
						query.setParameter(i, values.get(i));
					}
				}
				return query.iterate().next();
				// return
				// session.iterate(sql,values,types).next();
			}
		}));
	}

	public int count(final String sql, final Object[] values) {
		return getCountValue(this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.iterate().next();
				// return
				// session.iterate(sql,values,types).next();
			}
		}));
	}

	public int count(final String sql, final Object value, final Type type) {
		return getCountValue(this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				if (value != null) {
					query.setParameter(0, value, type);
				}
				return query.iterate().next();
			}
		}));
	}

	public int count(final String sql) {
		return getCountValue(this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery(sql).iterate().next();
			}
		}));
	}

	public List asList(Iterator iter) {
		List list = new ArrayList();
		while (iter.hasNext()) {
			list.add(iter.next());
		}
		return list;
	}
	
	public void save(Object obj) {
		try {
			this.getHibernateTemplate().save(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Object obj) {
		this.getHibernateTemplate().update(obj);
	}

	public void saveOrUpdate(Object obj) {
		this.getHibernateTemplate().saveOrUpdate(obj);
	}

	public void delete(Object obj) {
		this.getHibernateTemplate().delete(obj);
	}

	public void initialize(Object proxy) {
		this.getHibernateTemplate().initialize(proxy);
	}

	public void evict(Class classz, Serializable id) {
		try {
			this.getHibernateTemplate().getSessionFactory().evict(classz, id);
		} catch (HibernateException e) {
			e.printStackTrace(); // To
		}
	}
	
	public void evict(Class classz) {
		try {
			this.getHibernateTemplate().getSessionFactory().evict(classz);
		} catch (HibernateException e) {
			e.printStackTrace(); // To
		}
	}

	public void evictCollection(String name, Serializable id) {
		try {
			this.getHibernateTemplate().getSessionFactory().evictCollection(name, id);
		} catch (HibernateException e) {
			e.printStackTrace(); // To
		}
	}

	public void evictCollection(String name) {
		try {
			this.getHibernateTemplate().getSessionFactory().evictCollection(name);
		} catch (HibernateException e) {
			e.printStackTrace(); // To
		}
	}

	public void evictQueries() {
		try {
			this.getHibernateTemplate().getSessionFactory().evictQueries();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public void evictQueries(String region) {
		try {
			this.getHibernateTemplate().getSessionFactory().evictQueries(region);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public void debug() {
		try {
			System.err.println("connection id:" + this.getSession().connection() + "----------is connection readonly?----------" + this.getSession().connection().isReadOnly());
		} catch (SQLException e) {
			e.printStackTrace(); // To
		} catch (HibernateException e) {
			e.printStackTrace(); // To
		}
	}

	public void flush() {
		this.getHibernateTemplate().flush();
	}

	
}
