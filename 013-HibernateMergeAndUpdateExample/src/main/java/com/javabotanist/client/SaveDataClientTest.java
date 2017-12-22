package com.javabotanist.client;

import java.util.Date;

import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			
			Employee employee = new Employee();
			employee.setEmployeeName("Akhila Nithin");
			employee.setEmail("akhila@yahoo.com");
			employee.setSalary(90000.00);
			employee.setDoj(new Date());
			
			session.save(employee);
			session.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
