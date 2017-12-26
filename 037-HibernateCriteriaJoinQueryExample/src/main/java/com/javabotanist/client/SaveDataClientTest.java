package com.javabotanist.client;

import java.util.Date;

import org.hibernate.Session;

import com.javabotanist.entities.Call;
import com.javabotanist.entities.Person;
import com.javabotanist.entities.Phone;
import com.javabotanist.entities.PhoneType;
import com.javabotanist.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) {
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
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
			
			Call call1 = new Call();
			call1.setDuration(30);
			call1.setPhone(phone1);
			call1.setTimestamp(new Date());
			
			Call call2 = new Call();
			call2.setDuration(20);
			call2.setPhone(phone1);
			call2.setTimestamp(new Date());
			
			phone1.getCalls().add(call1);
			phone1.getCalls().add(call2);
			
			Person person2 = new Person();
			person2.setName("Nithin Prasad");
			person2.setNickName("NithPras");
			person2.setAddress("Sawdust Road, The Woodlands");
			person2.setCreatedOn(new Date());
			person2.setVersion(1);
			
			Phone phone2 = new Phone();
			phone2.setNumber("9073637380");
			phone2.setType(PhoneType.MOBILE);
			phone2.setPerson(person2);
			
			Call call3 = new Call();
			call3.setDuration(60);
			call3.setPhone(phone2);
			call3.setTimestamp(new Date());
			
			Call call4 = new Call();
			call4.setDuration(50);
			call4.setPhone(phone2);
			call4.setTimestamp(new Date());
			
			phone2.getCalls().add(call3);
			phone2.getCalls().add(call4);
			
			Phone phone3 = new Phone();
			phone3.setNumber("01828716288");
			phone3.setType(PhoneType.LAND_LINE);
			phone3.setPerson(person2);
			
			person2.getPhones().add(phone2);
			person2.getPhones().add(phone3);
			
			Call call5 = new Call();
			call5.setDuration(120);
			call5.setPhone(phone3);
			call5.setTimestamp(new Date());
			
			phone3.getCalls().add(call5);
			
			session.save(person1);
			session.save(person2);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
