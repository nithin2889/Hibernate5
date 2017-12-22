package com.javabotanist.client;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;

		try {
			session = sf.openSession();
			session.beginTransaction();

			Employee employee1 = new Employee();
			employee1.setBonus(new BigDecimal(200));
			employee1.setDesignation("HR Lead");
			employee1.setDoj(HibernateUtil.getDate("15/02/2016"));
			employee1.setEmail("amit.cs2226@gmail.com");
			employee1.setEmployeeName("Amit");
			employee1.setSalary(90000.00);

			Employee employee2 = new Employee();
			employee2.setBonus(new BigDecimal(200));
			employee2.setDesignation("Junior HR");
			employee2.setDoj(HibernateUtil.getDate("20/02/2017"));
			employee2.setEmail("nidhi3336@gmail.com");
			employee2.setEmployeeName("Nidhi");
			employee2.setSalary(50000.00);

			Department department1 = new Department();
			department1.setDepartmentLocation("Pune");
			department1.setDepartmentName("IT Technology");

			department1.getEmployees().add(employee1);
			department1.getEmployees().add(employee2);

			employee1.setDepartment(department1);
			employee2.setDepartment(department1);

			session.save(department1);

			Employee employee3 = new Employee();
			employee3.setBonus(new BigDecimal(300));
			employee3.setDesignation("IT Manager");
			employee3.setDoj(HibernateUtil.getDate("15/09/2016"));
			employee3.setEmail("kk.cs4446@gmail.com");
			employee3.setEmployeeName("Kishan");
			employee3.setSalary(80000.00);

			Employee employee4 = new Employee();
			employee4.setBonus(new BigDecimal(500));
			employee4.setDesignation("Junior IT Manager");
			employee4.setDoj(HibernateUtil.getDate("20/02/2016"));
			employee4.setEmail("john5556@gmail.com");
			employee4.setEmployeeName("John");
			employee4.setSalary(70000.00);

			Employee employee5 = new Employee();
			employee5.setBonus(new BigDecimal(500));
			employee5.setDesignation("Senior IT Manager");
			employee5.setDoj(HibernateUtil.getDate("20/07/2016"));
			employee5.setEmail("martin5556@gmail.com");
			employee5.setEmployeeName("Martin");
			employee5.setSalary(80000.00);

			Department department2 = new Department();

			department2.setDepartmentLocation("Mumbai");
			department2.setDepartmentName("IT Management");

			department2.getEmployees().add(employee3);
			department2.getEmployees().add(employee4);
			department2.getEmployees().add(employee5);

			employee3.setDepartment(department2);
			employee4.setDepartment(department2);
			employee5.setDepartment(department2);

			session.save(department2);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
