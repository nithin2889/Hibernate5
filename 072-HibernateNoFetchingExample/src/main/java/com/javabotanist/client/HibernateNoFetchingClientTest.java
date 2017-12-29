package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;


public class HibernateNoFetchingClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String userName = "ericm";
			String password = "eric#123";
			
			Employee employee = session.createQuery("select e "
					+ "from Employee e "
					+ "where "
					+ "e.userName = :userName and "
					+ "e.password = :password", Employee.class)
					.setParameter("userName", userName)
					.setParameter("password", password)
					.getSingleResult();

			if (employee != null) {
				System.out.println("::Employee Details::");
				System.out
						.println(employee.getId() + "\t" + employee.getEmployeeName() + "\t" + employee.getUserName());
				System.out.println("::Employee Department Details::");
				Department department = employee.getDepartment();
				if (department != null) {
					System.out.println("::Department Details::");
					System.out.println(department.getId() + "\t" + department.getDeptName());
				} else {
					System.out.println("Department not found.");
				}
			} else {
				System.out.println("Employee not found with provided credentials.");
			}
			
			// No Fetching (Scalar) Example. Here, Employee is not returned 
			// rather an Integer is returned and the value using which we can 
			// allow the user to login or logout.
			Integer accessLevel = session.createQuery("select e.accessLevel "
					+ "from Employee e "
					+ "where "
					+ "e.userName = :userName and "
					+ "e.password = :password", Integer.class)
				.setParameter("userName", userName)
				.setParameter("password", password)
				.getSingleResult();
			System.out.println("Access Level: "+accessLevel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}