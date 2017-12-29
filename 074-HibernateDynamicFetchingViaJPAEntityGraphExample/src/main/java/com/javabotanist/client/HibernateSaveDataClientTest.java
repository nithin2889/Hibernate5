package com.javabotanist.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.entities.Project;
import com.javabotanist.util.HibernateUtil;

public class HibernateSaveDataClientTest {

	public static void main(String[] args) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();

			Project project1 = new Project();
			project1.setProjectName("employee manangement");

			Project project2 = new Project();
			project2.setProjectName("hospital manangement");

			Employee employee1 = new Employee();
			employee1.setEmployeeName("Sean Murphy");
			employee1.setUserName("seanm");
			employee1.setPassword("pass#123");
			employee1.setAccessLevel(1);
			employee1.getProjects().add(project1);
			employee1.getProjects().add(project2);
			project1.getEmployees().add(employee1);
			project2.getEmployees().add(employee1);

			Employee employee2 = new Employee();
			employee2.setEmployeeName("Barry Johnson");
			employee2.setUserName("barryj");
			employee2.setPassword("barry@123");
			employee2.setAccessLevel(1);
			employee2.getProjects().add(project1);
			project1.getEmployees().add(employee2);
			employee2.getProjects().add(project2);
			project2.getEmployees().add(employee2);

			Employee employee3 = new Employee();
			employee3.setEmployeeName("Janet Warren");
			employee3.setUserName("janetw");
			employee3.setPassword("janet#123");
			employee3.setAccessLevel(1);
			employee3.getProjects().add(project1);
			project1.getEmployees().add(employee3);
			employee3.getProjects().add(project2);
			project2.getEmployees().add(employee3);

			Employee employee4 = new Employee();
			employee4.setEmployeeName("Pamela Smith");
			employee4.setUserName("seanm");
			employee4.setPassword("pamela#123");
			employee4.setAccessLevel(2);
			employee4.getProjects().add(project1);
			project1.getEmployees().add(employee4);

			Employee employee5 = new Employee();
			employee5.setEmployeeName("Eric Miller");
			employee5.setUserName("ericm");
			employee5.setPassword("eric#123");
			employee5.setAccessLevel(2);
			employee5.getProjects().add(project1);
			project1.getEmployees().add(employee5);

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

			session.persist(department1);
			session.persist(department2);

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}

}