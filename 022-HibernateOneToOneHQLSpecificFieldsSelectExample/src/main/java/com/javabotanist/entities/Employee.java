package com.javabotanist.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="employee")
@DynamicUpdate
public class Employee {

	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer employeeId;
	
	@Column(name="employee_name", length=200, nullable=false)
	private String employeeName;
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="date_of_joining")
	private Date doj;
	
	@Column(name="salary")
	private Double salary;
	
	// @OneToOne(cascade=CascadeType.ALL)
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="address_id")
	private Address address;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
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
