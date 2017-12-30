package com.javabotanist.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javabotanist.entities.Person;
import com.javabotanist.interceptors.LoggerInterceptor;
import com.javabotanist.util.HibernateUtil;

public class HibernateClientTest {

	private static final Logger logger = LogManager.getLogger(HibernateClientTest.class);

	public static void main(String[] args) {
		Transaction tx = null;
		// Session-1
		try (Session session = HibernateUtil.getSessionFactory()
				.withOptions() // Provides a SessionBuilder object
				.interceptor(new LoggerInterceptor()) // Registers our interceptor on Session level
				.openSession()) {
			tx = session.beginTransaction();
			
			Person person1 = new Person();
			person1.setPersonName("Sean Murphy");
			person1.setUsername("seanm");
			person1.setPassword("pass#123");
			person1.setAccessLevel(1);

			// Before saving any attribute the Logging interceptor will be called
			// as we have overridden the onSave() method.
			session.persist(person1);
			
			logger.info("Person Record is saved successfully");

			tx.commit();
		} catch (Exception e) {
			logger.error("Failed to save Person Records:" + e);
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}

		// Session-2
		try (Session session = HibernateUtil.getSessionFactory()
				.withOptions()
				.interceptor(new LoggerInterceptor())
				.openSession()) {
			// onLoad() method will be called as we are loading the previously saved data from the database.
			// onLoad() will only be called when Hibernate tries to load data from the database.
			Person person = session.get(Person.class, 1L);
			System.out.println(person);
		} catch (Exception e) {
			logger.error("Failed to fetch Record:" + e);
			throw e;
		}
	}

}