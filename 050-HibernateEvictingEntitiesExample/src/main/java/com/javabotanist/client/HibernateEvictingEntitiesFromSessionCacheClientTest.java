package com.javabotanist.client;

/**
 * 1. When the flush() method is called, the state of the entity is synchronized with the database. 
 * If you do not want synchronization to occur, or if you are processing a huge number of objects and need to 
 * manage memory efficiently, the evict() method can be used to remove the object and its collections from the 
 * first-level cache.
 * 
 * 2. To detach all entities from the current persistence context, clear() method is defined in both 
 * Entity Manager and Hibernate Session.
 * 
 * 3. To verify if an entity instance is currently attached to the running persistence context, both the EntityManager
 * and the Hibernate Session define a contains(entity) method.
 * 
 */

import org.hibernate.Session;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateEvictingEntitiesFromSessionCacheClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Long personId = 2L;
			Person person = session.get(Person.class, personId);
			System.out.println(person.getName());
			
			// Removes the person entity from the First-Level cache.
			// session.evict(person);
			
			// Removes all the entities from the First-Level cache.
			session.clear();
			System.out.println("-------------------------------");
			
			person = session.get(Person.class, personId);
			System.out.println(person.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}