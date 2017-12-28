package com.javabotanist.client;

import javax.persistence.EntityManager;

import com.javabotanist.entities.Book;
import com.javabotanist.entities.Person;
import com.javabotanist.util.JPAUtil;

public class HibernatePersistEntityUsingJPAClientTest {

	public static void main(String[] args) {
		EntityManager entityManager = null;
		try {
			Person author1 = new Person();
			author1.setName("Gavin King");
			
			Book book1 = new Book();
			book1.setIsbn("978-9730228236");
			book1.setTitle("Hibernate High-Performance Java Persistence");
			book1.setAuthor(author1);
			
			Book book2 = new Book();
			book2.setIsbn("900-9730228524");
			book2.setTitle("Hibernate Persistence Contexts");
			book2.setAuthor(author1);
			
			author1.getBooks().add(book1);
			author1.getBooks().add(book2);
			
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			entityManager.getTransaction().begin();
			entityManager.persist(author1);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entityManager != null)
				entityManager.close();
		}
	}

}