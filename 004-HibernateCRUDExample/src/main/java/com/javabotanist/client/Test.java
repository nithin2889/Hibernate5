package com.javabotanist.client;

import java.util.Date;

import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.service.EmployeeService;
import com.javabotanist.service.impl.EmployeeServiceImpl;
import com.javabotanist.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			EmployeeService employeeService = new EmployeeServiceImpl();
			
			createEmployee(employeeService);
			// getEmployeebyId(employeeService);
			// updateEmployeeById(employeeService);
			// deleteEmployeeById(employeeService);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void deleteEmployeeById(EmployeeService employeeService) {
		employeeService.deleteEmployeeById(2);
	}

	private static void updateEmployeeById(EmployeeService employeeService) {
		employeeService.updateEmployeeById(2, 120000.00);
	}

	private static void getEmployeebyId(EmployeeService employeeService) {
		Employee employeeId = employeeService.getEmployeeById(1);
		System.out.println(employeeId);
	}

	private static void createEmployee(EmployeeService employeeService) {
		employeeService.createEmployee(getEmployee());
	}
	
	private static Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeName("Akhila Nithin");
		employee.setEmail("akhila@yahoo.com");
		employee.setSalary(100000.00);
		employee.setDoj(new Date());
		
		return employee;
	}

}
