
package com.act.db.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.act.db.hibernate.HibernateUtil;

public class BaseDao {

	public static Object get(Class c, Serializable id) {
		Session session = HibernateUtil.getSession();
		
		Transaction tx = session.beginTransaction();
		Object o = session.get(c, id);
		session.close();
		return o;
	}

	@SuppressWarnings("unchecked")
	public static Object findOne(String sql, Object values[]) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(sql);
		query.setMaxResults(1);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		List list = query.list();
		session.close();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List findList(String sql) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(sql);
		List list = query.list();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List findList(String sql,int first,int limit) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(sql);
		if (first > 0) {
			query.setFirstResult(first);
		}
		if (limit > 0) {
			query.setMaxResults(limit);
		}
		List list = query.list();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List findList(String sql, Object values[]) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		List list = query.list();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List findList(String sql, Object values[],int first,int limit) {
		Session session = HibernateUtil.getSession();
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
		
		List list = query.list();
		session.close();
		return list;
	}
	
	/**
	 * if count * returns long, we still convert it to integer
	 * 
	 * @param obj
	 * @return
	 */
	public static int getCountValue(Object obj) {
		if (obj instanceof Integer) {
			return (Integer) obj;
		} else if (obj instanceof Long) {
			return ((Long) obj).intValue();
		}
		throw new IllegalArgumentException(obj + " is not a number for count value");
	}

}
