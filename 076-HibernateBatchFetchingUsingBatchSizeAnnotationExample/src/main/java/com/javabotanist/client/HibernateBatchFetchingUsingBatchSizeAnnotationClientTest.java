package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HibernateBatchFetchingUsingBatchSizeAnnotationClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Department> departments = session.createQuery("FROM Department", Department.class)
					.getResultList();
			
			for (Department department : departments) {
				System.out.println("::Department details::");
				System.out.println(department.getId() + "\t" + department.getDeptName());
				System.out.println("::Employees details::");
				List<Employee> employees = department.getEmployees();
				for (Employee employee : employees) {
					System.out.println(employee.getId() + "\t" + employee.getEmployeeName() 
						+ "\t" + employee.getUsername() + "\t" + employee.getPassword() 
						+ "\t" + employee.getAccessLevel());
				}
			}
		}
	}

}