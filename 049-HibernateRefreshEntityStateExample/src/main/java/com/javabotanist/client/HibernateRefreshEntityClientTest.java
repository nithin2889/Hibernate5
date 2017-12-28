package com.javabotanist.client;

import java.sql.Statement;

import org.hibernate.Session;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateRefreshEntityClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Person person = session.byId(Person.class).load(2L);
			session.doWork(connection -> {
				try(Statement statement = connection.createStatement()){
					statement.executeUpdate("UPDATE Person SET name=LOWER(name)");
				}
			});
			// 1. You can reload an entity instance and its collections at any time.
			// 2. One case where this is useful is, when it is known that the database state has changed 
			// since the data was updated.
			// 3. Refreshing allows the current database state to be pulled into the entity instance and 
			// the persistence context.
			// 4. Another case where this might be useful is when database triggers are used to initialize 
			// some of the properties of the entity.
			session.refresh(person);
			System.out.println(person.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}