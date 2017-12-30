package com.javabotanist.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

/**
 * 
 * 1. By mentioning @Audited, Hibernate is going to create an audit related table with _aud appended to it
 * in the database to maintain historical data. Second table is created for revision info.
 *
 * 2. For every DML performed into the entity table, a record will be inserted into the REVINFO and audit table..
 * 3. For every record inserted into the entity table, same records will be inserted into the audit table.
 * 
 */
@Audited
@Entity(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="first_name",length=100,nullable=false)
    private String firstName;
    
    @Column(name="last_name",length=100,nullable=false)
    private String lastName;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on")
    private Date createdOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", createdOn=" + createdOn
				+ "]";
	}
    
}
