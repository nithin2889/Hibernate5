package com.javabotanist.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	
	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;
	
	static {
		try {
			if(sessionFactory == null) {
				standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
				MetadataSources metaDataSources = new MetadataSources(standardServiceRegistry);
				Metadata metadata = metaDataSources.getMetadataBuilder().build();
				sessionFactory = metadata.buildSessionFactory();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(standardServiceRegistry != null) {
				StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
			}
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
