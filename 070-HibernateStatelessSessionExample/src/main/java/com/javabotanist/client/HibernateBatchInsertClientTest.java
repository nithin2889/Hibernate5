package com.javabotanist.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateBatchInsertClientTest {

	public static void main(String[] args) {
		Transaction tx = null;
		int batchSize = 5;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			
			for(int i=0; i<1000; i++) {
				Person person = new Person();
				person.setFirstName("Nithin-"+i);
				person.setLastName("Prasad-"+i);
				
				session.persist(person);
				
				if(i>0 && i % batchSize == 0) {
					System.out.println("Flushing and Clearing the session");
					// Flush a batch of inserts and release memory.
					// flush() - the object in question will be inserted into the database.
					session.flush();
					// clear() - deletes all the objects stored in the first-level cache.
					session.clear();
				}
			}
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive()) 
				tx.rollback();
			throw e;
		}
	}

}