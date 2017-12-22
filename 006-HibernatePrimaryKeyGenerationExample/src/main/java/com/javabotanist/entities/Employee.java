package com.javabotanist.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="employee")
@DynamicUpdate
public class Employee {

	@Id
	@Column(name="employee_id")
	// @GeneratedValue(strategy=GenerationType.AUTO, generator="empid_generator")
	// @SequenceGenerator(name="empid_generator", initialValue=1, allocationSize=1, sequenceName="empid_seq")
	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	// @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="empid_gen")
	// @SequenceGenerator(name="empid_gen", initialValue=1, allocationSize=1, sequenceName="empid_seq")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="empid_gen")
	@TableGenerator(name="empid_gen", initialValue=1, allocationSize=1, table="empid_seq")
	private Integer employeeId;
	
	@Column(name="employee_name", length=100, nullable=false)
	private String employeeName;
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="date_of_joining")
	private Date doj;
	
	@Column(name="salary")
	private Double salary;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email + ", doj="
				+ doj + ", salary=" + salary + "]";
	}
	
}
