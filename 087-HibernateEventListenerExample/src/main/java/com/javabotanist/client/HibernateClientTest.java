package com.javabotanist.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateClientTest {

	private static final Logger logger = LogManager.getLogger(HibernateClientTest.class);

	public static void main(String[] args) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();

			Person person = new Person();
			person.setPersonName("Sean Murphy");
			person.setUsername("seanm");
			person.setPassword("pass#123");
			person.setAccessLevel(1);

			// Invokes SaveOrUpdateEventListener event listener and passes this object to it.
			session.saveOrUpdate(person);

			logger.info("Person Record is saved successfully");

			tx.commit();
			System.out.println("------------------------------------------------------------------");
			long perosnId = 1L;
			
			// Invokes LoadEventListener event listener and passes this object to it.
			Person person1 = session.get(Person.class, perosnId);
			if(person1 != null){
				tx = session.beginTransaction();
				person1.setAccessLevel(2);				
				session.update(person1);
				tx.commit();
			}
			System.out.println("------------------------------------------------------------------");
			
			// Invokes RefreshEventListener event listener and passes this object to it.
			session.refresh(person1);
			
		} catch (Exception e) {
			logger.error("Failed to save Person Records:" + e);
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}

}