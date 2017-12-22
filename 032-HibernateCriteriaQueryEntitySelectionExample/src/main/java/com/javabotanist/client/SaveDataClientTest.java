package com.javabotanist.client;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			
			Employee employee1 = new Employee();
			employee1.setEmployeeName("Nithin Prasad");
			employee1.setEmail("nithin@yahoo.com");
			employee1.setSalary(100000.00);
			employee1.setDoj(new Date());
			
			Employee employee2 = new Employee();
			employee2.setEmployeeName("Akhila Nithin");
			employee2.setEmail("akhila@yahoo.com");
			employee2.setSalary(90000.00);
			employee2.setDoj(new Date());
			
			session.save(employee1);
			session.save(employee2);
			
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

}
