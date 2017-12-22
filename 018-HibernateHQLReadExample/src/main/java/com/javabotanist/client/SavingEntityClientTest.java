package com.javabotanist.client;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class SavingEntityClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			
			Employee employee1 = new Employee();
			employee1.setEmployeeName("Nithin Prasad");
			employee1.setDesignation("Sr. Apps Developer");
			employee1.setDoj(new Date());
			employee1.setEmail("nithin@yahoo.com");
			employee1.setBonus(new BigDecimal(2000));
			employee1.setSalary(120000.00);
			
			Employee employee2 = new Employee();
			employee2.setEmployeeName("Akhila Nithin");
			employee2.setDesignation("Technology Analyst");
			employee2.setDoj(new Date());
			employee2.setEmail("akhila@yahoo.com");
			employee2.setBonus(new BigDecimal(2500));
			employee2.setSalary(100000.00);
			
			session.save(employee1);
			session.save(employee2);
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
