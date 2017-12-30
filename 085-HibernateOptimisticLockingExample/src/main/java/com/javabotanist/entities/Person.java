package com.javabotanist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="person")
@DynamicUpdate
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="person_name",length=60)
    private String personName;
    
    @NaturalId
    @Column(name="user_name",length=60,nullable=false)
    private String userName;
    
    @Column(name="password",nullable=false)
    private String password;
    
    @Column(name="accress_level")
    private int accessLevel;
    
    @Version
    @Column(name="version")
    private long version;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	public int getAccessLevel() {
		return accessLevel;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
