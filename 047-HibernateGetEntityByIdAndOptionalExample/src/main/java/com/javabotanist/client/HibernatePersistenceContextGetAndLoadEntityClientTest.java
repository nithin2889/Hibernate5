package com.javabotanist.client;

import java.util.Optional;

import org.hibernate.Session;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernatePersistenceContextGetAndLoadEntityClientTest {

	public static void main(String[] args) {
		getPersonById();
		loadPersonById();
	}
	
	private static void loadPersonById() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			long personId = 2L;
			Optional<Person> optionalPerson = session.byId(Person.class).loadOptional(personId);
			if(optionalPerson.isPresent()) {
				Person person = optionalPerson.get();
				System.out.println(person.getId()+"\t"+person.getName());
			} else {
				System.out.println("Person doesn't exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getPersonById() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			long personId = 2L;
			Person person = session.byId(Person.class).load(personId);
			if(person != null) {
				System.out.println(person.getId()+"\t"+person.getName());
			} else {
				System.out.println("Person doesn't exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}