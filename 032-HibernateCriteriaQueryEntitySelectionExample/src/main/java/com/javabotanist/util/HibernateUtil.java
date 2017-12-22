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
				// Create a StandardServiceRegistry
				standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
				// Create a MetadataSources
				MetadataSources metaDataSources = new MetadataSources(standardServiceRegistry);
				// Create a Metadata
				Metadata metadata = metaDataSources.getMetadataBuilder().build();
				// Create a SessionFactory
				sessionFactory = metadata.buildSessionFactory();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(standardServiceRegistry != null) {
				StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
			}
		}
	}
	
	// Utility method to return the session factory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
