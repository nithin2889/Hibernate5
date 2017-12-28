package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.entities.Book;
import com.javabotanist.entities.Isbn;
import com.javabotanist.util.HibernateUtil;

public class HibernateReadByNaturalIdClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			System.out.println("Read Entity By Natural ID");
			Book book = session.byNaturalId(Book.class).using("isbn", new Isbn("933-9730228524", "944-9730228524")).load();
			System.out.println(book.getTitle());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}