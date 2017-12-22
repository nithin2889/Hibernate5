package com.javabotanist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="student_table")
public class Student extends Person {

	@Column(name="school_name", length=50)
	private String schoolName;
	
	@Column(name="fee")
	private float fee;
	
	@Column(name="section_name", length=50)
	private String sectionName;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	@Override
	public String toString() {
		return "Student [schoolName=" + schoolName + ", fee=" + fee + ", sectionName=" + sectionName + "]" + super.toString();
	}
	
}
