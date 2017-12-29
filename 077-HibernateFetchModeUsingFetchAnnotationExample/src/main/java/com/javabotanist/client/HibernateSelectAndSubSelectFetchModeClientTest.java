package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.util.HibernateUtil;

public class HibernateSelectAndSubSelectFetchModeClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Department> departments = session.createQuery("FROM Department", Department.class)
					.getResultList();
			System.out.println("Fetched Department Size: " + departments.size());
			
			for (Department department : departments) {
				System.out.println("Department ID: "+department.getId());
				System.out.println("Employees Count: "+department.getEmployees().size());
			}
		}
	}

}