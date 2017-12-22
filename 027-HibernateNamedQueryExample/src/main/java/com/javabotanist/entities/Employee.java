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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "employee_table")
@NamedQueries(value= {
@NamedQuery(name="getTotalSalaryByDept", query="SELECT dept.departmentName, SUM(emp.salary) FROM Department dept "
+ "LEFT JOIN dept.employees emp GROUP BY dept "
+ "HAVING SUM(emp.salary) > 200000"),
@NamedQuery(name="Employee.byId", query="FROM Employee WHERE employeeId=:empId")})
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
