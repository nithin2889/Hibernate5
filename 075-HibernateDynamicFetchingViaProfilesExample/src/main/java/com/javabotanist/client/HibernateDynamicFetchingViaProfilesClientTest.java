package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.entities.Project;
import com.javabotanist.util.HibernateUtil;

public class HibernateDynamicFetchingViaProfilesClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String userName = "ericm";
			
			session.enableFetchProfile("employee.projects");
			Employee employee = session.bySimpleNaturalId(Employee.class).load(userName);
			
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