package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			String SQL = "SELECT version()";
			String result = (String) session.createNativeQuery(SQL).getSingleResult();
			System.out.println("POSTGres version: " + result);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
