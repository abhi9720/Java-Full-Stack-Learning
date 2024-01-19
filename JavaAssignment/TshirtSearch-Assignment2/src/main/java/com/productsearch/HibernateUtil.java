package com.productsearch;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory(); 
	private static SessionFactory buildSessionFactory() {
		SessionFactory sf =  null;
		 

			Configuration con =  new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(TShirt.class);

			ServiceRegistry reg =  new ServiceRegistryBuilder()
					.applySettings(con.getProperties())
					.buildServiceRegistry();

			sf =  con.buildSessionFactory(reg);
		
		 
		return sf;
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
