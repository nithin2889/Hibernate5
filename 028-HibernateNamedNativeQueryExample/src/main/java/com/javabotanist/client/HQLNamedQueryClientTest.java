package com.javabotanist.client;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HQLNamedQueryClientTest {

	public static void main(String[] args) {
		getTotalSalaryOfEmployeesByDept();
		getEmployeeById();
	}

	private static void getEmployeeById() {
		int empId = 1;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			Query<Employee> query = session.createNamedQuery("Employee.byId");
			query.setParameter("empId", empId);
			Employee employee = query.uniqueResult();
			System.out.println(employee);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	private static void getTotalSalaryOfEmployeesByDept() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			Query<Object[]> query = session.createNamedQuery("getTotalSalaryByDept");
			List<Object[]> list = query.list();
			
			for (Object[] objects : list) {
				String departmentName = (String)objects[0];
				Double totalSalByDept = (Double)objects[1];
				
				System.out.println("Department Name: " + departmentName);
				System.out.println("Total Salary By Department: " + totalSalByDept);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

}
