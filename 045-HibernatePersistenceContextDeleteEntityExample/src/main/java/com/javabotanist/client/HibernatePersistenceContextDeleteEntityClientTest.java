package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernatePersistenceContextDeleteEntityClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			long personId = 1L;
			Person person = session.get(Person.class, personId);
			if(person != null) {
				session.beginTransaction();
				session.delete(person);
				session.getTransaction().commit();
			} else {
				System.out.println("Person doesn't exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}