package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Book;
import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateMappingJava8DateTimeClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Long personId = 1L;
			Person person = session.get(Person.class, personId);
			System.out.println(":::::Person Details:::::");
			System.out.println(person.getId() + "\t" + person.getName());
			System.out.println(":::::List Of Books:::::");
			List<Book> books = person.getBooks();
			for (Book book : books) {
				System.out.println(book.getId()+"\t"+book.getTitle()+"\t"+book.getIsbn());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}