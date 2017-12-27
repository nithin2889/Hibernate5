package com.javabotanist.client;

import java.text.ParseException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) throws ParseException {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			Employee employee1 = new Employee();
			employee1.setDesignation("Developer");
			employee1.setName("Nithin");
			employee1.setSalary(150000.00);
			
			Employee employee2 = new Employee();
			employee2.setDesignation("Tester");
			employee2.setName("Akhila");
			employee2.setSalary(100000.00);
			
			Employee employee3 = new Employee();
			employee3.setDesignation("Scrum Master");
			employee3.setName("Barry Johnson");
			employee3.setSalary(200000.00);
			
			Employee employee4 = new Employee();
			employee4.setDesignation("Associate");
			employee4.setName("Arnold");
			employee4.setSalary(80000.00);
			
			Employee employee5 = new Employee();
			employee5.setDesignation("Sr. Associate");
			employee5.setName("Harry");
			employee5.setSalary(120000.00);
			
			Employee employee6 = new Employee();
			employee6.setDesignation("Manager");
			employee6.setName("Richard");
			employee6.setSalary(250000.00);
			
			Employee employee7 = new Employee();
			employee7.setDesignation("VP");
			employee7.setName("Anthony Andrews");
			employee7.setSalary(300000.00);
			
			session.beginTransaction();
			
			session.save(employee1);
			session.save(employee2);
			session.save(employee3);
			session.save(employee4);
			session.save(employee5);
			session.save(employee6);
			session.save(employee7);
			
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

}
