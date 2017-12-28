package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.entities.Account;
import com.javabotanist.util.HibernateUtil;

public class HibernateFetchDerivedPropertyForAnEntityClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			long accountId = 1L;
			
			Account account = session.get(Account.class, accountId);
			
			System.out.println("Account ID: "+account.getId());
			System.out.println("Rate Amount: "+account.getRate());
			System.out.println("Credit Amount: "+account.getCredit());
			
			System.out.println("Interest Amount: "+account.getInterest());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}