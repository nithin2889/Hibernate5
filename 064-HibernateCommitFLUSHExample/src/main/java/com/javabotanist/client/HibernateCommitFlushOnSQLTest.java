package com.javabotanist.client;

import java.math.BigInteger;

import javax.persistence.FlushModeType;

import org.hibernate.Session;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

/**
 * AUTO - The Session is sometimes flushed before query execution.
 * SESSION - The Session is only flushed prior to a transaction commit.
 * ALWAYS - The Session is always flushed before query execution.
 * MANUAL - The Session can only be manually flushed.
 * @author Nithin
 *
 */

public class HibernateCommitFlushOnSQLTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Person person = new Person();
			person.setFirstName("Gavin");
			person.setLastName("King");
			
			session.beginTransaction();
			session.save(person); // triggers insert queries
			session.getTransaction().commit(); // commits and outputs the current database state for this session as it is committed.
			
			BigInteger count = (BigInteger)session.createNativeQuery("select count(*) from Person")
				.setFlushMode(FlushModeType.COMMIT)
				.getSingleResult();
			
			System.out.println(count);
			
			// session.getTransaction().commit(); // outputs 0 as the database state is not committed yet. 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}