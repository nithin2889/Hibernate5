package com.javabotanist.service;

import com.javabotanist.entities.Employee;

public interface EmployeeService {
	
	public void createEmployee(Employee employee);
	public Employee getEmployeeById(int employeeId);
	public void updateEmployeeById(int employeeId, Double newSal);
	public void deleteEmployeeById(int employeeId);

}
