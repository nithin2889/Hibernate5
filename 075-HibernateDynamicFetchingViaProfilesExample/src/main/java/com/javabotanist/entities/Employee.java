package com.javabotanist.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfile.FetchOverride;
import org.hibernate.annotations.FetchProfiles;
import org.hibernate.annotations.NaturalId;

/**
 * Before using @Profiles we need to have atleast one @NaturalId, because Profiles
 * always fetches data based on NaturalId.
 * @author Nithin
 *
 */

@Entity
@Table(name="employee")
@FetchProfiles(value={
	@FetchProfile(name="employee.projects", 
		fetchOverrides={@FetchOverride(association="projects", entity=Employee.class, 
		mode=FetchMode.JOIN)})
})
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name="employee_name",length=60)
	private String employeeName;
	
	@NaturalId
	@Column(name="user_name",length=60,nullable=false)
	private String userName;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="access_level")
	private int accessLevel;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	
	@ManyToMany(mappedBy="employees",cascade=CascadeType.PERSIST)
	private List<Project> projects = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", userName=" + userName + ", password="
				+ password + ", accessLevel=" + accessLevel + ", department=" + department + "]";
	}
	
}
