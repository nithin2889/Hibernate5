package com.javabotanist.client;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateBatchUpdateClientTest {

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		ScrollableResults scrollableResults = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();
			tx.begin();
			
			int batchSize = 5;
			scrollableResults = session.createQuery("select p from Person p")
					.setCacheMode(CacheMode.IGNORE)
					.scroll(ScrollMode.FORWARD_ONLY);
			
			int count = 0;
			while(scrollableResults.next()) {
				Person person = (Person)scrollableResults.get(0);
				processPerson(person);
				if(++count % batchSize == 0) {
					// flush a batch of updates and release memory
					System.out.println("Session is flushed");
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null && tx.isActive()) 
				tx.rollback();
			throw e;
		} finally {
			if(scrollableResults != null) {
				scrollableResults.close();
			}
			if(session != null) {
				session.close();
			}
		}
	}

	private static void processPerson(Person person) {
		person.setFirstName(person.getFirstName() + "_updated");
	}

}