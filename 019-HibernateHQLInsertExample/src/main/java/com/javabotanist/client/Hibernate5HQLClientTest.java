package com.javabotanist.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.javabotanist.util.HibernateUtil;

public class Hibernate5HQLClientTest {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		insertEmployeeRecord(sf);
	}

	private static void insertEmployeeRecord(SessionFactory sf) {
		try (Session session = sf.openSession()) {
			String HQL = "INSERT INTO Employee(employeeName, doj, salary, bonus, designation, email)"
					+ "SELECT employeeName, doj, salary, bonus, designation, email FROM BackupEmployee";

			Query query = session.createQuery(HQL);
			session.beginTransaction();
			int executeUpdate = query.executeUpdate();
			if (executeUpdate > 0)
				System.out.println(executeUpdate + " records inserted successfully!");
			else
				System.out.println("No records inserted");
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
