package com.javabotanist.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Isbn implements Serializable {
	
	private static final long serialVersionUID = -4641189159960373004L;
	private String isbn1;
	private String isbn2;
	
	public Isbn() {}
	
	public Isbn(String isbn1, String isbn2) {
		super();
		this.isbn1 = isbn1;
		this.isbn2 = isbn2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn1 == null) ? 0 : isbn1.hashCode());
		result = prime * result + ((isbn2 == null) ? 0 : isbn2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Isbn other = (Isbn) obj;
		if (isbn1 == null) {
			if (other.isbn1 != null)
				return false;
		} else if (!isbn1.equals(other.isbn1))
			return false;
		if (isbn2 == null) {
			if (other.isbn2 != null)
				return false;
		} else if (!isbn2.equals(other.isbn2))
			return false;
		return true;
	}
	
}
