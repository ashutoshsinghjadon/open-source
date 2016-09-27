package com.exam.server.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Session Factory single-ton class
 * Author Ashutosh
 */

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	/**
	/** singleton for getting session factory object
	 * @param args
	 */
	public static SessionFactory getSessionFactory(){
		if(sessionFactory==null){
			Configuration configuration=new Configuration();
			configuration.configure();
			ServiceRegistry sr= new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory=configuration.buildSessionFactory(sr);
		}
		return sessionFactory;
	}
}
