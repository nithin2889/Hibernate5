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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 1. Transactional − Use this strategy for read-mostly data where it is critical 
 * to prevent stale data in concurrent transactions, in the rare case of an update.
 * 
 * 2. Read-write − Again use this strategy for read-mostly data where it is critical 
 * to prevent stale data in concurrent transactions, in the rare case of an update.
 * 
 * 3. Nonstrict-read-write − This strategy makes no guarantee of consistency between 
 * the cache and the database. Use this strategy if data hardly ever changes and a 
 * small likelihood of stale data is not of critical concern.
 * 
 * 4. Read-only − A concurrency strategy suitable for data, which never changes. Use it 
 * for reference data only.
 */

@Entity
@Table(name="department")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Department {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="dept_id")
    private Long deptId;
	
	@Column(name="dept_name",nullable=false,unique=false)
	private String deptName;
	
	@OneToMany(mappedBy="department",cascade=CascadeType.ALL)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Employee> employees = new ArrayList<>();

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
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

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", employees=" + employees + "]";
	}
	
}
