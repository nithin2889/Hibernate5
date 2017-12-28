package com.javabotanist.client;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Book;
import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateFetchBinaryDataAndBLOBClientTest {

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
			Blob profilePic = person.getProfilePic();
			InputStream stream = profilePic.getBinaryStream();
			Files.copy(stream, Paths.get("outputProfilePics/"+person.getName()+".jpg"), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}