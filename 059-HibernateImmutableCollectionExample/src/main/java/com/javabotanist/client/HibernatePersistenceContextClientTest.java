package com.javabotanist.client;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Batch;
import com.javabotanist.entities.Event;
import com.javabotanist.util.HibernateUtil;

public class HibernatePersistenceContextClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// createBatch(session);
			// updateBatchInfo(session);
			// updateBatchEvents(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void updateBatchEvents(Session session) {
		long batchId = 1L;

		Batch batch = session.get(Batch.class, batchId);
		if (batch != null) {
			session.beginTransaction();
			List<Event> events = batch.getEvents();
			events.clear();
			session.delete(batch);
			session.getTransaction().commit();
		} else {
			System.out.println("Batch not found with id: " + batch);
		}
	}

	private static void updateBatchInfo(Session session) {
		long batchId = 1L;

		Batch batch = session.get(Batch.class, batchId);
		if (batch != null) {
			session.beginTransaction();
			batch.setName("Proposed change request");

			session.update(batch);
			session.getTransaction().commit();
		} else {
			System.out.println("Batch not found with id: " + batch);
		}
	}

	private static void createBatch(Session session) {
		Event event1 = new Event();
		event1.setCreatedOn(new Date());
		event1.setMessage("Event 1");

		Event event2 = new Event();
		event2.setCreatedOn(new Date());
		event2.setMessage("Event 2");

		Batch batch = new Batch();
		batch.setName("Change Request");

		batch.getEvents().add(event1);
		batch.getEvents().add(event2);

		session.beginTransaction();
		session.save(batch);
		session.getTransaction().commit();
	}

}