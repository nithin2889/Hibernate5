package com.javabotanist.client;

import java.util.Date;

import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			createEmployee(session);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void createEmployee(Session session) {
		session.beginTransaction();
		Integer id = (Integer) session.save(getEmployee());
		System.out.println("Employee created with id: " + id);
		session.getTransaction().commit();
	}
	
	private static Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeName("Akhila Nithin");
		employee.setEmail("akhila@yahoo.com");
		employee.setSalary(100000.00);
		employee.setDoj(new Date());
		
		return employee;
	}

}
