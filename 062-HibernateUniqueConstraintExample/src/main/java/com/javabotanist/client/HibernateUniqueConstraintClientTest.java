package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.entities.Author;
import com.javabotanist.entities.Book;
import com.javabotanist.util.HibernateUtil;

/**
 * This will throw a ConstraintViolationException if we try to insert duplicate entries.
 * @author Nithin
 *
 */
public class HibernateUniqueConstraintClientTest {

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
			
			Book book2 = new Book();
			book2.setBookPrice(600.00);
			book2.setIsbn("901-12172383823");
			book2.setTitle("IT!");
			book2.setAuthor(author);
			
			author.getBooks().add(book1);
			author.getBooks().add(book2);
			
			session.beginTransaction();
			session.save(author);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}