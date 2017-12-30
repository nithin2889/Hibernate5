package com.javabotanist.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateSaveDataClientTest {
	
	private static final Logger logger = LogManager.getLogger(HibernateSaveDataClientTest.class);

	public static void main(String[] args) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();

			Person person1 = new Person();
			person1.setPersonName("Sean Murphy");
			person1.setUsername("seanm");
			person1.setPassword("pass#123");
			person1.setAccessLevel(1);

			Person person2 = new Person();
			person2.setPersonName("Barry Johnson");
			person2.setUsername("barryj");
			person2.setPassword("barry@123");
			person2.setAccessLevel(1);

			session.persist(person1);
			session.persist(person2);
			
			logger.info("Person Records saved successfully");

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}

}