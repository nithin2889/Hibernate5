package com.javabotanist.client;

import javax.persistence.EntityManager;

import org.hibernate.engine.spi.SessionImplementor;

import com.javabotanist.entities.Person;
import com.javabotanist.util.JPAUtil;

public class HibernateReadEntityHibernateJPAClientTest {
	
	public static void main(String[] args) {
		SessionImplementor sessionImplementor = null;
		try {
			EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
			sessionImplementor = entityManager.unwrap(SessionImplementor.class);
			Person person = sessionImplementor.get(Person.class, 2L);
			System.out.println(person.getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionImplementor != null)
				sessionImplementor.close();
		}
	}

}