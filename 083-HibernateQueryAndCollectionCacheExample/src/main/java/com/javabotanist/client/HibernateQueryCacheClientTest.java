package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HibernateQueryCacheClientTest {

	public static void main(String[] args) {
		Session session = null;
		String SELECT_EMPLOYEE_HQL = "FROM Employee";
		// Session-1
		try {
			// Getting employee list from DATABASE
			System.out.println(":::Getting Employee list from DATABASE from Session-1:::");
			session = HibernateUtil.getSessionFactory().openSession();

			@SuppressWarnings("unchecked")
			List<Employee> employees = session.createQuery(SELECT_EMPLOYEE_HQL)
				.setCacheable(true)
				.setCacheRegion("employee.cache")
				.list();

			for (Employee employee : employees) {
				System.out.println("Employee Name : " + employee.getEmployeeName());
				System.out.println("Employee UserName : " + employee.getUsername());
				System.out.println("Employee Passsword : " + employee.getPassword());
				System.out.println("Employee AccessLevel : " + employee.getAccessLevel());

				System.out.println("-------------------------------------------------");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		// Session-2
		try {
			/// Getting employee list from Cached result
			System.out.println(":::Getting Employee list from DATABASE from Session-2:::");
			session = HibernateUtil.getSessionFactory().openSession();

			@SuppressWarnings("unchecked")
			List<Employee> employees = session.createQuery(SELECT_EMPLOYEE_HQL)
				.setCacheable(true)
				.setCacheRegion("employee.cache")
				.list();
			for (Employee employee : employees) {
				System.out.println("Employee Name : " + employee.getEmployeeName());
				System.out.println("Employee UserName : " + employee.getUsername());
				System.out.println("Employee Passsword : " + employee.getPassword());
				System.out.println("Employee AccessLevel : " + employee.getAccessLevel());
				System.out.println("-------------------------------------------------");
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