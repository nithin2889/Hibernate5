package com.javabotanist.dao;

import com.javabotanist.entities.Employee;

public interface EmployeeDAO {

	public void addEmployee(Employee employee);
	public Employee fetchEmployeeById(int employeeId);
	public void updateEmployeeById(int employeeId, Double newSal);
	public void deleteEmployeeById(int employeeId);
	
}
