package com.javabotanist.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateSimulatingOptimisticLockingClientTest {

	public static void main(String[] args) {
		Long personId = 1L;
		
		// As thread t1 sleeps for 5 seconds, thread t2 will start its job and updates the table, and when t1 resumes 
		// it senses a data change with the help of version column and throws OptimisticLockException.
		Thread t1 = new Thread(new Runnable() {
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;

			@Override
			public void run() {
				Person person = session1.get(Person.class, personId);
				if (person != null) {
					tx = session1.beginTransaction();

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					person.setPassword("pass@34");
					session1.update(person);
					tx.commit();
				}
			}
		});

		// As thread t1 sleeps for 5 seconds, thread t2 will start its job and updates the table.
		Thread t2 = new Thread(new Runnable() {
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;

			@Override
			public void run() {
				Person person = session2.get(Person.class, personId);
				if (person != null) {
					tx = session2.beginTransaction();
					person.setPassword("pass#234");
					session2.update(person);
					tx.commit();
				}
			}
		});

		t1.start();
		t2.start();
	}

}