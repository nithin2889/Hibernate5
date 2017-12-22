package com.javabotanist.client;

import java.util.Date;

import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			session.beginTransaction();
			/*
			 * Session#persist() -> This method is used to save an entity/object into the database and returns a void 
			 * It will throw an exception if an entity already exists in the database.
			 */
			session.persist(getEmployee1());
			
			/*
			 * Session#save() -> This method is used to save an entity/object into the database and returns a generated primary key. 
			 * It will throw an exception if an entity already exists in the database.
			 */
			Integer id = (Integer) session.save(getEmployee2());
			System.out.println("Employee created with id: " +id);
			
			/*
			 * Session#saveOrUpdate() -> This method is used to either save or update an entity in the database.
			 */
			session.saveOrUpdate(getEmployee3());
			
			session.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static Employee getEmployee3() {
		Employee employee = new Employee();
		employee.setEmployeeName("Brooke Burns");
		employee.setEmail("brooke@yahoo.com");
		employee.setSalary(80000.00);
		employee.setDoj(new Date());
		
		return employee;
	}
	
	private static Employee getEmployee1() {
		Employee employee = new Employee();
		employee.setEmployeeName("Nithin Prasad");
		employee.setEmail("nithin@yahoo.com");
		employee.setSalary(100000.00);
		employee.setDoj(new Date());
		
		return employee;
	}
	
	private static Employee getEmployee2() {
		Employee employee = new Employee();
		employee.setEmployeeName("Akhila Nithin");
		employee.setEmail("akhila@yahoo.com");
		employee.setSalary(100000.00);
		employee.setDoj(new Date());
		
		return employee;
	}

}
