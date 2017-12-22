package com.javabotanist.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="employee_address")
public class Address {

	@Id
	@Column(name="address_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId;
	
	@Column(name="street_name", length=50)
	private String street;
	
	@Column(name="city_name", length=50)
	private String city;
	
	@Column(name="state_name")
	private String state;
	
	@Column(name="pincode")
	private Long pincode;
	
	@ManyToMany(mappedBy="addressList")
	private Collection<Employee> empList = new ArrayList<>();
	
	public Collection<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(Collection<Employee> empList) {
		this.empList = empList;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
	}
	
}
