
package com.act.db.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	private static final SessionFactory sessionFactory;
	static {

		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	public static Session getSession() {
		Session s = (Session) session.get();
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	public static void closeSession() {
		Session s = (Session) session.get();
		session.set(null);
		if (s != null)
			s.close();
	}
}
