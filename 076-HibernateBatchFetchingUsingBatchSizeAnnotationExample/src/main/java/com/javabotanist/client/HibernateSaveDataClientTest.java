package com.javabotanist.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HibernateSaveDataClientTest {

	public static void main(String[] args) {
		Transaction tx =  null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
			tx = session.beginTransaction();
			
			Employee employee1 = new Employee();
			employee1.setEmployeeName("Sean Murphy");
			employee1.setUserName("seanm");
			employee1.setPassword("pass#123");
			employee1.setAccessLevel(1);
			
			Employee employee2 = new Employee();
			employee2.setEmployeeName("Barry Johnson");
			employee2.setUserName("barryj");
			employee2.setPassword("barry@123");
			employee2.setAccessLevel(1);
			
			Employee employee3 = new Employee();
			employee3.setEmployeeName("Janet Warren");
			employee3.setUserName("janetw");
			employee3.setPassword("janet#123");
			employee3.setAccessLevel(1);
			
			Employee employee4 = new Employee();
			employee4.setEmployeeName("Pamela Smith");
			employee4.setUserName("pamelas");
			employee4.setPassword("pamela#123");
			employee4.setAccessLevel(2);
			
			Employee employee5 = new Employee();
			employee5.setEmployeeName("Eric Miller");
			employee5.setUserName("ericm");
			employee5.setPassword("eric#123");
			employee5.setAccessLevel(2);
			
			Employee employee6 = new Employee();
			employee6.setEmployeeName("Habib Miller");
			employee6.setUserName("habibm");
			employee6.setPassword("habib#123");
			employee6.setAccessLevel(3);
			
			Employee employee7 = new Employee();
			employee7.setEmployeeName("Hilary Miller");
			employee7.setUserName("hilarym");
			employee7.setPassword("hilary#123");
			employee7.setAccessLevel(3);
			
			Department department1 = new Department();
			department1.setDeptName("IT");
			
			department1.getEmployees().add(employee1);
			employee1.setDepartment(department1);
			
			department1.getEmployees().add(employee2);
			employee2.setDepartment(department1);
			
			department1.getEmployees().add(employee3);
			employee3.setDepartment(department1);
			
			Department department2 = new Department();
			department2.setDeptName("Finance");
			
			department2.getEmployees().add(employee4);
			employee4.setDepartment(department2);
			department2.getEmployees().add(employee5);
			employee5.setDepartment(department2);
			
			Department department3 = new Department();
			department3.setDeptName("HR");
			
			department3.getEmployees().add(employee6);
			employee6.setDepartment(department3);
			department3.getEmployees().add(employee7);
			employee7.setDepartment(department3);
			
			session.persist(department1);
			session.persist(department2);
			session.persist(department3);
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}

}