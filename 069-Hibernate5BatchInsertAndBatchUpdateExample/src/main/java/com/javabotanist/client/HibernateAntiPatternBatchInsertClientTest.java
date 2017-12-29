package com.javabotanist.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateAntiPatternBatchInsertClientTest {

	public static void main(String[] args) {
		Transaction tx = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			
			for(int i=0; i<100; i++) {
				Person person = new Person();
				person.setFirstName("Nithin-"+i);
				person.setLastName("Prasad-"+i);
				
				session.persist(person);
			}
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive()) 
				tx.rollback();
			throw e;
		}
	}

}