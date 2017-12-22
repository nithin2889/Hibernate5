package com.javabotanist.client;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javabotanist.model.EmployeeStatistics;
import com.javabotanist.util.HibernateUtil;

public class AggregateFunctionsInHQLClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			String HQL = "SELECT new com.javabotanist.model.EmployeeStatistics(COUNT(e), AVG(e.salary), MIN(e.salary), "
					+ "MAX(e.salary), SUM(e.salary)) FROM Employee e";
			
			Query<EmployeeStatistics> query = session.createQuery(HQL, EmployeeStatistics.class);
			EmployeeStatistics employeeStatistics = query.uniqueResult();
			
			System.out.println("Total count of employees: " + employeeStatistics.getEmpCount());
			System.out.println("Average salary of employees: " + employeeStatistics.getAvgSal()); 
			System.out.println("Min Salary of Employees: " + employeeStatistics.getMinSal()); 
			System.out.println("Max Salary of Employees: " + employeeStatistics.getMaxSal());
			System.out.println("Sum of Salary of Employees: " + employeeStatistics.getSumSal());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
