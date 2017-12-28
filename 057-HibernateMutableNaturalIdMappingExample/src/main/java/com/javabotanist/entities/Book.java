package com.javabotanist.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="book")
@DynamicUpdate
public class Book {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name="title",length=100)
    private String title;

    @NaturalId(mutable=true)
    private String isbn;

    @ManyToOne
    private Person author;
    
    @Temporal(value=TemporalType.DATE)
    @Column(name="published_date")
    private Date publishedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", isbn=" + isbn + ", author=" + author + ", publishedDate="
				+ publishedDate + "]";
	}
    
}
