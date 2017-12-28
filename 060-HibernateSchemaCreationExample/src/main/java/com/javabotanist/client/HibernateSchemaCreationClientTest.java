package com.javabotanist.client;

import com.javabotanist.util.HibernateUtil;

public class HibernateSchemaCreationClientTest {

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory();
			System.out.println("Session Factory is created..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}