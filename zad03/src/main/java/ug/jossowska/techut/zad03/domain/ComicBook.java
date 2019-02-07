package ug.jossowska.techut.zad03.domain;

import java.sql.Date;

public class ComicBook {

	
	int id;	
	String title;
	double price;
	Date dateOfRelease;
	boolean isPopular;
	
	public ComicBook() {
		super();
	}
	
	public ComicBook(String title, Date dateOfRelease, boolean isPopular, double price) {
		super();
		this.title = title;
		this.dateOfRelease = dateOfRelease;
		this.price = price;
		this.isPopular = isPopular;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public void setDateOfRelease(Date dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean getIsPopular(){
		return isPopular;
	}
	public void setIsPopular(boolean isPopular) {
		this.isPopular = isPopular;
	}
}
