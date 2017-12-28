package com.javabotanist.client;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.javabotanist.entities.Book;
import com.javabotanist.entities.Person;
import com.javabotanist.util.JPAUtil;

public class HibernatePersistEntityHibernateClientTest {

	public static void main(String[] args) {
		EntityManager entityManager = null;
		try {
			Person author1 = new Person();
			author1.setName("CLRS");

			Book book1 = new Book();
			book1.setIsbn("979-6762378382");
			book1.setTitle("Introduction To Algorithms ");
			book1.setAuthor(author1);

			Book book2 = new Book();
			book2.setIsbn("901-9236736283");
			book2.setTitle("Algorithms Revisited");
			book2.setAuthor(author1);

			author1.getBooks().add(book1);
			author1.getBooks().add(book2);
			
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
			Session session = entityManager.unwrap(Session.class);
			session.getTransaction().begin();
			session.persist(author1);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entityManager != null)
				entityManager.close();
		}
	}

}