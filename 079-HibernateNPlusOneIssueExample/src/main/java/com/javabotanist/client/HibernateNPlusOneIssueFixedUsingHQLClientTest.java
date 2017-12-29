package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HibernateNPlusOneIssueFixedUsingHQLClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			/**
			 * N+1 problem: This means, 1 query is getting triggered 
			 * for the Parent table where as N queries are getting triggered for the Child table. 
			 * That is, first query populates the primary object and the second query 
			 * populates child object for each of the unique primary objects returned.	
			 */
			// List<Department> departments = session.createQuery("FROM Department", Department.class).getResultList();
			
			/**
			 * Below query will avoid N+1 problem. When writing an HQL or a JPQL we must use a key
			 * called FETCH as shown below. Hibernate will trigger only one query with a JOIN thereby
			 * avoiding N+1 problem.
			 */
			List<Department> departments = session.createQuery("FROM Department d LEFT JOIN FETCH d.employees", Department.class).getResultList();
			
			for (Department department : departments) {
				System.out.println("::DEPARTMENT DETAILS::");
				System.out.println(department.getId()+"\t"+department.getDeptName());
				List<Employee> employees = department.getEmployees();
				System.out.println("::EMPLOYEE DETAILS::");
				for (Employee employee : employees) {
					System.out.println(employee.getId()+"\t"+employee.getEmployeeName()+"\t"+
							employee.getUsername()+"\t"+employee.getPassword()+"\t"+
							employee.getAccessLevel());
				}
			}
		}
	}

}