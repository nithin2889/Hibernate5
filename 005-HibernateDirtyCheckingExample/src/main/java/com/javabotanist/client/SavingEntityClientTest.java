package com.javabotanist.client;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class SavingEntityClientTest {

	public static void main(String[] args) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			createEmployee(session);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	private static void createEmployee(Session session) {
		session.beginTransaction();
		Integer id = (Integer) session.save(getEmployee());
		System.out.println("Employee created with id: " +id);
		session.getTransaction().commit();
	}
	
	private static Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeName("Nithin Prasad");
		employee.setEmail("nithin@yahoo.com");
		employee.setSalary(120000.00);
		employee.setDoj(new Date());
		
		return employee;
	}

}
