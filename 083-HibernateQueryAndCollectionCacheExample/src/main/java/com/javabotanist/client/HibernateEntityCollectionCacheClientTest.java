package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HibernateEntityCollectionCacheClientTest {

	public static void main(String[] args) {
		Session session = null;
		try {
			// Getting Department from DATABASE
			System.out.println(":::Getting Department from DATABASE:::");
			Long deptId = 1L;
			
			session = HibernateUtil.getSessionFactory().openSession();
			Department department = session.get(Department.class, deptId);
			System.out.println("Department Name:" + department.getDeptName());
			
			List<Employee> employees = department.getEmployees();
			for (Employee employee : employees) {
				System.out.println("Employee Name : " + employee.getEmployeeName());
				System.out.println("Employee UserName : " + employee.getUsername());
				System.out.println("Employee Passsword : " + employee.getPassword());
				System.out.println("Employee AccessLevel : " + employee.getAccessLevel());

				System.out.println("---------------------------------------------");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		try {
			// Get Department from Cache
			System.out.println(":::Getting Department from Cache:::");
			Long deptId = 1L;
			
			session = HibernateUtil.getSessionFactory().openSession();
			Department department = session.get(Department.class, deptId);
			System.out.println("Department Name:" + department.getDeptName());
			
			List<Employee> employees = department.getEmployees();
			for (Employee employee : employees) {
				System.out.println("Employee Name : " + employee.getEmployeeName());
				System.out.println("Employee UserName : " + employee.getUsername());
				System.out.println("Employee Passsword : " + employee.getPassword());
				System.out.println("Employee AccessLevel : " + employee.getAccessLevel());

				System.out.println("---------------------------------------------");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}