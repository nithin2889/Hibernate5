package com.javabotanist.client;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) throws ParseException {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			
			Person person1 = new Person();
			person1.setName("Mark Bingel");
			person1.setNickName("Mac");
			person1.setAddress("Alameda Street Los Angeles");
			person1.setCreatedOn(new Date());
			person1.setVersion(1);
			
			Person person2 = new Person();
			person2.setName("Sean Murphy");
			person2.setNickName("Sam");
			person2.setAddress("Bank of Canada,234 Wellington Street");
			person2.setCreatedOn(new Date());
			person2.setVersion(1);

			session.save(person1);
			session.save(person2);
			
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

}
