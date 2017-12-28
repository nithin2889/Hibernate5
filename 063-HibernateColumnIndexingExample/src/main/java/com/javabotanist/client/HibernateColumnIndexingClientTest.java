package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.entities.Author;
import com.javabotanist.entities.Book;
import com.javabotanist.util.HibernateUtil;

public class HibernateColumnIndexingClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Author author = new Author();
			author.setFirstName("Stephen");
			author.setLastName("King");
			
			Book book1 = new Book();
			book1.setBookPrice(900.00);
			book1.setIsbn("900-12172383823");
			book1.setTitle("IT!");
			book1.setAuthor(author);
			
			author.getBooks().add(book1);
			
			session.beginTransaction();
			session.save(author);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}