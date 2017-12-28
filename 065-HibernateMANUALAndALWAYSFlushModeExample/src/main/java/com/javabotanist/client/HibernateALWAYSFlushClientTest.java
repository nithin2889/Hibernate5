package com.javabotanist.client;

import java.math.BigInteger;

import org.hibernate.FlushMode;
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

public class HibernateALWAYSFlushClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.setHibernateFlushMode(FlushMode.ALWAYS);
			
			Person  person = new Person();
			person.setFirstName("Gavin");
			person.setLastName("King");
			
			session.beginTransaction();
			session.save(person);
			
			BigInteger count = (BigInteger) session.createNativeQuery("select count(*) from Person").uniqueResult();
			System.out.println("Count of person: " + count);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}