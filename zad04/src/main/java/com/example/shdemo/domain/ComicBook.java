package com.example.shdemo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "comicbook.unPopular", query = "Select c from ComicBook c where c.isPopular = false")
})
public class ComicBook {

	private Long id;
	private String title;
	private Date dateOfRelease;
	private Boolean isPopular = false;
	private Double price;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public Date getDateOfRelease() {
		return dateOfRelease;
	}

	public void setdateOfRelease(Date dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}

	public Boolean getIsPopular() {
		return isPopular;
	}

	public void setIsPopular(Boolean isPopular) {
		this.isPopular = isPopular;
	}
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
