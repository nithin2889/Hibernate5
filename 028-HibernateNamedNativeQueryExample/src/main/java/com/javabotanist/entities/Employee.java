package com.javabotanist.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "employee_table")
@NamedNativeQueries(value= {
@NamedNativeQuery(name="getTotalSalaryByDept", query="select dept.dept_name as dept_name, sum(emp.salary) as total_salary "
		+ "from department_table dept "
		+ "left outer join employee_table emp "
		+ "on dept.dept_id=emp.dept_id "
		+ "group by dept.dept_id "
		+ "having sum(emp.salary)>200000"),
@NamedNativeQuery(name="Employee.byId", query="SELECT * FROM employee_table WHERE employee_id=:empId", resultClass=Employee.class)})
public class Employee {

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;

	@Column(name = "employee_name", length = 60, nullable = false)
	private String employeeName;

	@Column(name = "date_of_joining")
	private Date doj;

	@Column(name = "salary")
	private Double salary;

	@Column(name = "bonus")
	private BigDecimal bonus;

	@Column(name = "designation", length = 50)
	private String designation;

	@Column(name = "email", unique = true, length = 50)
	private String email;

	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Department department;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", doj=" + doj + ", salary="
				+ salary + ", bonus=" + bonus + ", designation=" + designation + ", email=" + email + ", department="
				+ department + "]";
	}
	
}
