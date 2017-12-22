package com.javabotanist.client;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.javabotanist.entities.Person;
import com.javabotanist.entities.Phone;
import com.javabotanist.entities.PhoneType;
import com.javabotanist.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;

		try {
			session = sf.openSession();
			session.beginTransaction();

			Person person1 = new Person();
			person1.setName("Mark Bingel");
			person1.setNickName("Mac");
			person1.setAddress("Alameda Street Los Angeles");
			person1.setCreatedOn(new Date());
			person1.setVersion(1);

			Phone phone1 = new Phone();
			phone1.setNumber("9073637380");
			phone1.setType(PhoneType.MOBILE);
			phone1.setPerson(person1);

			person1.getPhones().add(phone1);

			Person person2 = new Person();
			person2.setName("Sean Murphy");
			person2.setNickName("Sam");
			person2.setAddress("Bank of Canada,234 Wellington Street");
			person2.setCreatedOn(new Date());
			person2.setVersion(1);

			Phone phone2 = new Phone();
			phone2.setNumber("809865430");
			phone2.setType(PhoneType.MOBILE);
			phone2.setPerson(person2);

			Phone phone3 = new Phone();
			phone3.setNumber("022909742");
			phone3.setType(PhoneType.LAND_LINE);
			phone3.setPerson(person2);

			person2.getPhones().add(phone2);
			person2.getPhones().add(phone3);

			session.save(person1);
			session.save(person2);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
