package com.javabotanist.client;

import java.util.Date;

import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) {

		Employee employee1 = new Employee();
		employee1.setEmployeeName("Nithin Prasad");
		employee1.setEmail("nithin@yahoo.com");
		employee1.setSalary(100000.00);
		employee1.setDoj(new Date());

		Employee employee2 = new Employee();
		employee2.setEmployeeName("Grey Nicholls");
		employee2.setEmail("grey@yahoo.com");
		employee2.setSalary(50000.00);
		employee2.setDoj(new Date());

		Employee employee3 = new Employee();
		employee3.setEmployeeName("Jen Hensen");
		employee3.setEmail("jenhen@yahoo.com");
		employee3.setSalary(60000.00);
		employee3.setDoj(new Date());

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			
			session.persist(employee1);
			session.persist(employee2);
			session.persist(employee3);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
