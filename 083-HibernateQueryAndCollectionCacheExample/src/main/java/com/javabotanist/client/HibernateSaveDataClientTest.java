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
			
			Department department1 = new Department();
			department1.setDeptName("IT");
			department1.getEmployees().add(employee1);
			department1.getEmployees().add(employee2);
			
			employee1.setDepartment(department1);
			employee2.setDepartment(department1);
			
			session.persist(department1);
			
			tx.commit();
			
		} catch (Exception e) {
			if(tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}

}