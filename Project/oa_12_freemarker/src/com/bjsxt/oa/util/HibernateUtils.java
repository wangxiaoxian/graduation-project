package com.bjsxt.oa.util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
	private static SessionFactory factory;
	private HibernateUtils() {}
	static {
		Configuration cfg = new Configuration().configure();
		factory = cfg.buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	
	public static Session getSession() {
		return factory.openSession();
	}
	
	public static void closeSession(Session session) {
		session.close();
	}
}
