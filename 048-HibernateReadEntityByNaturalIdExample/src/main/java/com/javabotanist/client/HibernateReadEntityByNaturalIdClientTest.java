package com.javabotanist.client;

import java.util.Optional;

import org.hibernate.Session;

import com.javabotanist.entities.Book;
import com.javabotanist.util.HibernateUtil;

public class HibernateReadEntityByNaturalIdClientTest {

	public static void main(String[] args) {
		// loadBookBySimpleNaturalId();
		// loadBookByNaturalId();
		// loadBookByOptionalJava8NaturalId();
	}
	
	private static void loadBookByOptionalJava8NaturalId() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String naturalId = "978-9730228236";
			
			// loadOptional() method is used when the id in question is sure to be present.
			Optional<Book> optionalBook = session.byNaturalId(Book.class).using("isbn", naturalId).loadOptional();
			if(optionalBook.isPresent()) {
				Book book = optionalBook.get();
				System.out.println(book.getTitle() + "\t" + book.getAuthor().getName());
			} else {
				System.out.println("Book info not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadBookByNaturalId() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String naturalId = "978-9730228236";
			
			// load() method is used when the id in question is sure to be present.
			Book book = session.byNaturalId(Book.class).using("isbn", naturalId).load();
			if(book != null) {
				System.out.println(book.getTitle() + "\t" + book.getAuthor().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadBookBySimpleNaturalId() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String naturalId = "978-9730228236";
			
			// getReference() method is used when the id in question is assumed to exist.
			Book book = session.bySimpleNaturalId(Book.class).getReference(naturalId);
			if(book != null) {
				System.out.println(book.getTitle() + "\t" + book.getAuthor().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}