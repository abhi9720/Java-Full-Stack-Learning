package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.user;

public class HibernateUtils {

	private static final SessionFactory  sessionfactory=  buildSession();
	private static SessionFactory buildSession() {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = config.buildSessionFactory();
		return sf;
	}
	public static SessionFactory getSessionfactory() {
		return sessionfactory;
	}
	
	
	
}
