package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.entities.Book;
import com.javabotanist.util.HibernateUtil;

public class HibernateReadAndUpdateByNaturalIdClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			Long bookId = 1L;
			Book book = session.get(Book.class, bookId);
			if(book != null) {
				session.beginTransaction();
				book.setIsbn("999-9237862828");
				session.getTransaction().commit();
			}
			System.out.println("--------------------------");
			Book load = session.byNaturalId(Book.class).using("isbn", "999-9237862828").load();
			System.out.println(load.getTitle());
		}
	}

}