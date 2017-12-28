package com.javabotanist.client;

import java.util.Date;
import java.util.TimeZone;

import org.hibernate.Session;

import com.javabotanist.entities.Book;
import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernatePersistenceEntityClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory()
				.withOptions()
				.jdbcTimeZone(TimeZone.getTimeZone("UTC"))
				.openSession()) {
			Person author1 = new Person();
			author1.setName("Gavin King");
			
			Date publishedDate = new Date();
			
			Book book1 = new Book();
			book1.setIsbn("978-9730228236");
			book1.setTitle("Hibernate High-Performance Java Persistence");
			book1.setPublishedDate(publishedDate);
			book1.setAuthor(author1);
			
			Book book2 = new Book();
			book2.setIsbn("900-9730228524");
			book2.setTitle("Hibernate Persistence Contexts");
			book2.setPublishedDate(publishedDate);
			book2.setAuthor(author1);
			
			author1.getBooks().add(book1);
			author1.getBooks().add(book2);
			
			session.beginTransaction();
			session.save(author1);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}