package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateSchemaCreationClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Person person1 = new Person();
			person1.setName("Nithin Prasad");
			person1.setEmail("nithin@gmail.com");
			
			Person person2 = new Person();
			person2.setClientId(10001L);
			person2.setEmail("akhila@gmail.com");
			
			session.beginTransaction();
			
			session.save(person1);
			session.save(person2);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}