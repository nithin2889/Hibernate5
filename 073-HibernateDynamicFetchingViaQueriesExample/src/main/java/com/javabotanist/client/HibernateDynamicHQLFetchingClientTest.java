package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.entities.Project;
import com.javabotanist.util.HibernateUtil;


public class HibernateDynamicHQLFetchingClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Dynamic HQL fetching example.
			String userName = "ericm";
			String password = "eric#123";
			
			Employee employee = session.createQuery("select e "
					+ "from Employee e "
					+ "left join fetch e.projects "
					+ "where "
					+ "e.userName = :userName and "
					+ "e.password = :password", Employee.class)
					.setParameter("userName", userName)
					.setParameter("password", password)
					.getSingleResult();

			if (employee != null) {
				System.out.println("::Employee Details::");
				System.out.println(employee.getId()+"\t"+employee.getEmployeeName()+"\t"+
					employee.getUserName()+"\t"+employee.getAccessLevel());
				
				List<Project> projects = employee.getProjects();
				System.out.println("::Project Details::");
				for (Project project : projects) {
					System.out.println(project.getId()+"\t"+project.getProjectName());
				}
				
				System.out.println("::Department Details::");
				Department department = employee.getDepartment();
				if(department != null) {
					System.out.println(department.getId()+"\t"+department.getDeptName());
				} else {
					System.out.println("Department details not found!");
				}
			} else {
				System.out.println("Employee not found with provided credentials.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}