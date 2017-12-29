package com.javabotanist.client;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.entities.Project;
import com.javabotanist.util.HibernateUtil;

public class HibernateDynamicFetchingUsingJPASubGraphClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Long projectId = 1L;
			Project project = session.find(Project.class, projectId, 
					Collections.singletonMap("javax.persistence.fetchgraph", 
							session.getEntityGraph("project.employees")));
			
			System.out.println("::Project Details::");
			System.out.println(project.getId()+"\t"+project.getProjectName());
			System.out.println("::Employee Details::");
			List<Employee> employees = project.getEmployees();
			for (Employee employee : employees) {
				System.out.println(employee.getId()+"\t"+employee.getEmployeeName()+"\t"+
					employee.getUserName()+"\t"+employee.getAccessLevel());
				
				Department department = employee.getDepartment();
				System.out.println("::Employee's Department Details::");
				if(department != null) {
					System.out.println(department.getId()+"\t"+department.getDeptName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}