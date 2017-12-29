package com.javabotanist.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.entities.Project;
import com.javabotanist.util.HibernateUtil;


public class HibernateDynamicQueryFetchingClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String userName = "ericm";
			String password = "eric#123";
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
			Root<Employee> root = query.from(Employee.class);
			root.fetch("projects", JoinType.LEFT);
			
			query.select(root).where(
				builder.and(
					builder.equal(root.get("userName"), userName),
					builder.equal(root.get("password"), password))
			);
			
			Employee employee = session.createQuery(query).getSingleResult();
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