package com.javabotanist.client;

import java.util.Date;

import org.hibernate.Session;

import com.javabotanist.entities.Address;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			createEmployee(session);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void createEmployee(Session session) {
		session.beginTransaction();
		// Integer id = (Integer) session.save(getEmployee());
		// System.out.println("Employee created with id: " + id);
		session.persist(getEmployee());
		session.getTransaction().commit();
	}
	
	private static Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeName("Nithin Prasad");
		employee.setEmail("nithin@yahoo.com");
		employee.setSalary(100000.00);
		employee.setDoj(new Date());
		
		Address address1 = new Address();
		address1.setStreet("Sawdust Road");
		address1.setCity("The Woodlands");
		address1.setState("Texas");
		address1.setPincode(77380L);
		
		Address address2 = new Address();
		address2.setStreet("KF Road");
		address2.setCity("Bangalore");
		address2.setState("Karnataka");
		address2.setPincode(560061L);
				
		employee.getAddressList().add(address1);
		employee.getAddressList().add(address2);
		
		address1.setEmployee(employee);
		address2.setEmployee(employee);
		
		return employee;
	}

}
