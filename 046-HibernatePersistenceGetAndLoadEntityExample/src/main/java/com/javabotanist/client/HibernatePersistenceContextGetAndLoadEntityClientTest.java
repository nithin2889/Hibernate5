package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Book;
import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernatePersistenceContextGetAndLoadEntityClientTest {

	public static void main(String[] args) {
		// getPersonById();
		loadPersonById();
	}
	
	private static void loadPersonById() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			long personId = 3L;
			// The load() doesn't hit the database, an object that represents the proxy and not a database row.
			// Throws exception if it doesn't find the row.
			// Only when you are sure that the specified id exists in the database use load() method.
			Person person = session.load(Person.class, personId);
			if(person != null) {
				List<Book> book = person.getBooks();
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
			// The get() always hits the database, an object that represents the database row.
			// Returns null if it doesn't find the row. When get() method is called, it tries to find the data in the 
			// first level cache and if not present no proxy object is created and hence it is called as Eager Loading.
			Person person = session.get(Person.class, personId);
			if(person != null) {
				List<Book> book = person.getBooks();
				System.out.println(person.getId()+"\t"+person.getName());
			} else {
				System.out.println("Person doesn't exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}