package com.javabotanist.DTO;

public class EmployeeDTO {

	private String employeeName;
	private String email;
	private Double salary;
	
	public EmployeeDTO(String employeeName, String email, Double salary) {
		super();
		this.employeeName = employeeName;
		this.email = email;
		this.salary = salary;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getEmail() {
		return email;
	}

	public Double getSalary() {
		return salary;
	}
	
}
