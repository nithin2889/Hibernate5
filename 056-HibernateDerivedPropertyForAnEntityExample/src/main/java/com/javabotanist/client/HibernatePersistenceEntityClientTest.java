package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.entities.Account;
import com.javabotanist.util.HibernateUtil;

public class HibernatePersistenceEntityClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Account account = new Account();
			account.setCredit(9000.00);
			account.setRate(8.50);
			
			session.beginTransaction();
			session.save(account);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}