package com.javabotanist.client;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.entities.Project;
import com.javabotanist.util.HibernateUtil;

public class HibernateDynamicFetchingUsingJPAGraphClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Long employeeId = 1L;
			
			Employee employee = session.find(Employee.class, employeeId, 
					Collections.singletonMap("avax.persistence.fetchgraph", "employee.projects"));
			
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