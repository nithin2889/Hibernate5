package com.javabotanist.client;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

/**
 * A StatelessSession has no persistence context associated with it and does not provide many of the 
 * higher-level life cycle semantics.
 * 
 * Some of the things not provided by a StatelessSession include:
 * 1. First-Level Cache
 * 2. Interaction with any second-level or query-cache.
 * 3. Transactional write-behind or automatic dirty checking.
 * 
 * Limitations of a StatelessSession
 * 1. Operations performed using a stateless session never cascades	to associated instances.
 * 2. Collections are ignored by a stateless session.
 * 3. Lazy loading of associations is not supported.
 * 4. Operations performed via a stateless session bypass Hibernate's event model and interceptors.
 * 5. Due to lack of a first-level cache, Stateless sessions are vulnerable to data aliasing effects.
 * 6. A stateless session is a lower-level abstraction that is much closer to the underlying JDBC.
 * 
 * @author Nithin
 *
 */

public class HibernateStatelessSessionBatchUpdateClientTest {

	public static void main(String[] args) {
		StatelessSession statelessSession = null;
		Transaction tx = null;
		ScrollableResults scrollableResults = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			statelessSession = sessionFactory.openStatelessSession();
			
			tx = statelessSession.getTransaction();
			tx.begin();
			
			scrollableResults = statelessSession.createQuery("select p from Person p")
					.scroll(ScrollMode.FORWARD_ONLY);
			
			while(scrollableResults.next()) {
				Person person = (Person) scrollableResults.get(0);
				processPerson(person);
				statelessSession.update(person);
			}
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null && tx.getStatus() == TransactionStatus.ACTIVE) 
				tx.rollback();
			throw e;
		} finally {
			if(scrollableResults != null) {	
				scrollableResults.close();
			}
			if(statelessSession != null) {
				statelessSession.close();
			}
		}
	}

	private static void processPerson(Person person) {
		person.setFirstName(person.getFirstName()+"_updated");
	}

}