package com.javabotanist.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.javabotanist.util.HibernateUtil;

public class Hibernate5HQLClientTest {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		// updateEmployeeEmailById(sf);
		// deleteEmployeeById(sf);
	}

	private static void deleteEmployeeById(SessionFactory sf) {
		int empId = 2;
		try (Session session = sf.openSession()) {
			String HQL = "DELETE FROM Employee WHERE employeeId=:empId";

			Query query = session.createQuery(HQL);
			query.setParameter("empId", empId);
			
			session.beginTransaction();
			
			int executeUpdate = query.executeUpdate();
			if (executeUpdate > 0)
				System.out.println("Employee " + empId + " deleted successfully!");
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void updateEmployeeEmailById(SessionFactory sf) {
		int empId = 2;
		String newEmail = "akhi@yahoo.com";
		try (Session session = sf.openSession()) {
			String HQL = "UPDATE Employee SET email=:newEmail WHERE employeeId=:empId";

			Query query = session.createQuery(HQL);
			query.setParameter("newEmail", newEmail);
			query.setParameter("empId", empId);
			
			session.beginTransaction();
			
			int executeUpdate = query.executeUpdate();
			if (executeUpdate > 0)
				System.out.println("Employee email updated successfully!");
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
