package com.javabotanist.client;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class DeleteDataClientTest {

	public static void main(String[] args) {
		deleteEmployeeById();
	}

	private static void deleteEmployeeById() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Employee employee = session.get(Employee.class, 2);
			if(employee != null) {
				session.beginTransaction();
				session.delete(employee);
				session.getTransaction().commit();
			}
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

}
