package com.javabotanist.service.impl;

import com.javabotanist.dao.impl.EmployeeDAOImpl;
import com.javabotanist.entities.Employee;
import com.javabotanist.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public void createEmployee(Employee employee) {
		new EmployeeDAOImpl().addEmployee(employee);
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		return new EmployeeDAOImpl().fetchEmployeeById(employeeId);
	}

	@Override
	public void updateEmployeeById(int employeeId, Double newSal) {
		new EmployeeDAOImpl().updateEmployeeById(employeeId, newSal);
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		new EmployeeDAOImpl().deleteEmployeeById(employeeId);
	}

}
