package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HibernateJoinFetchModeClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Long departmentId = 1L;
			Department department = session.get(Department.class, departmentId);
			if(department != null) {
				System.out.println("Department ID: "+department.getId());
				List<Employee> employees = department.getEmployees();
				System.out.println("Employees Count: "+employees.size());
			} else {
				System.out.println("Department details not found with id "+departmentId);
			}
		}
	}

}