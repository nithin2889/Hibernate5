package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HibernateLazyCollectionClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Long departmentId = 1L;
			Department department = session.get(Department.class, departmentId);
			if (department != null) {
				System.out.println("Department ID:" + department.getId());
				List<Employee> employees = department.getEmployees();
				// Hibernate will fire a query for selecting the max value of the order_id
				// column and adds 1 as it is 0 based.
				System.out.println("Employees count:" + employees.size());
				for (Employee employee : employees) {
					System.out.println(employee.getEmployeeName() + "\t" + employee.getUsername());
				}
			} else {
				System.out.println("Department details not found with ID: " + departmentId);
			}
		}
	}

}