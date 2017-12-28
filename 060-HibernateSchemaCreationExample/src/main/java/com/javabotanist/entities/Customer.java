package com.javabotanist.entities;

import java.sql.Blob;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "customer_name", length = 100, nullable = false)
	private String name;

	@Basic(fetch = FetchType.LAZY)
	private UUID accountsPayableXrefId;

	@Lob
	@Column(name = "customer_pic")
	private Blob image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getAccountsPayableXrefId() {
		return accountsPayableXrefId;
	}

	public void setAccountsPayableXrefId(UUID accountsPayableXrefId) {
		this.accountsPayableXrefId = accountsPayableXrefId;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}
}