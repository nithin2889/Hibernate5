package com.javabotanist.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

public class HibernateUtil {
	
	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;
	
	static {
		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
		
		// Hibernate settings which is equivalent to hibernate.cfg.xml properties
		Map<String, String> dbSettings = new HashMap<>();
		dbSettings.put(Environment.URL, "jdbc:postgresql://localhost:5432/hibernate_test_db");
		dbSettings.put(Environment.USER, "postgres");
		dbSettings.put(Environment.PASS, "password");
		dbSettings.put(Environment.DRIVER, "org.postgresql.Driver");
		dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
		
		// Apply database settings
		registryBuilder.applySettings(dbSettings);
		// Creating Registry
		standardServiceRegistry = registryBuilder.build();
		// Creating MetadataSources
		MetadataSources sources = new MetadataSources(standardServiceRegistry);
		// Creating Metadata
		Metadata metadata = sources.getMetadataBuilder().build();
		// Creating SessionFactory
		sessionFactory = metadata.buildSessionFactory();
	}
	
	// Utility method to return the session factory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
