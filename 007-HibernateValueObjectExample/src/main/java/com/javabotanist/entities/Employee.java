package com.javabotanist.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.javabotanist.model.Address;

// Employee is an entity object which has meaning on its own.
@Entity
@Table(name="employee")
@DynamicUpdate
public class Employee {

	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer employeeId;
	
	@Column(name="employee_name", length=100, nullable=false)
	private String employeeName;
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="date_of_joining")
	private Date doj;
	
	@Column(name="salary")
	private Double salary;
	
	// Address is a value object and has no meaning on its own and to tell Hibernate 
	// not to create a different table we annotate the field with @Embedded
	@Embedded
	@AttributeOverrides(value = {
		@AttributeOverride(column = @Column(name="home_street_name", length=50), name = "street"),
		@AttributeOverride(column = @Column(name="home_city_name", length=50), name = "city"),
		@AttributeOverride(column = @Column(name="home_state_name"), name = "state"),
		@AttributeOverride(column = @Column(name="home_pincode"), name = "pincode")
	})
	private Address homeAddress;
	
	@Embedded
	@AttributeOverrides(value = {
		@AttributeOverride(column = @Column(name="office_street_name", length=50), name = "street"),
		@AttributeOverride(column = @Column(name="office_city_name", length=50), name = "city"),
		@AttributeOverride(column = @Column(name="office_state_name"), name = "state"),
		@AttributeOverride(column = @Column(name="office_pincode"), name = "pincode")
	})
	private Address officeAddress;
	
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
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
