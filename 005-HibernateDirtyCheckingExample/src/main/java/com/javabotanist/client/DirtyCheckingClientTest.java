package com.javabotanist.client;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class DirtyCheckingClientTest {

	public static void main(String[] args) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Employee employee = session.get(Employee.class, 1);
			if(employee != null) {
				session.beginTransaction();
				/*
				 * Even though we are not calling the update method explicitly, Hibernate will trigger an update
				 * for every line between the begin transaction and commit transaction which is known as dirty checking. 
				 */
				employee.setSalary(130000.00);
				// session.update(employee);
				session.getTransaction().commit();
			} else {
				System.out.println("Employee doesn't exist");
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

}
