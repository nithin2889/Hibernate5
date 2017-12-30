package com.javabotanist.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateOptimisticLockingClientTest {

	public static void main(String[] args) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Long personId = 1L;
			Person person = session.get(Person.class, personId);
			if(person != null) {
				/**
				 * Once we update the password column, the version column will be explicitly updated by Hibernate.
				 * In a multi-threaded environment, if one thread is updated a record which is also being updated by 
				 * another thread then, Hibernate will throw OptimisticLockException instead updating again.
				 */
				tx = session.beginTransaction();
				person.setPassword("nithpras@123");
				session.update(person);
				tx.commit();
			} else {
				System.out.println("Person details not found for "+personId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive())
				tx.rollback();
		}
	}

}