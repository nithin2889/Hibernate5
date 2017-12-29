package com.javabotanist.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="department")
public class Department {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
	
	@Column(name="dept_name",length=100,nullable=false)
	private String deptName;
	
	// While using LazyCollectionOption, Order Column must be specified. If not, the 
	// underlying List data type will be considered as a Hibernate Type BagType which 
	// will be unordered.
	@OneToMany(mappedBy="department",cascade=CascadeType.PERSIST)
	@OrderColumn(name="order_id")
	@LazyCollection(value=LazyCollectionOption.EXTRA)
	private List<Employee> employees = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}
