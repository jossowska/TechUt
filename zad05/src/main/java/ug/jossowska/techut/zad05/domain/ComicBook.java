package ug.jossowska.techut.zad05.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
@NamedQueries({
	@NamedQuery(name = "comicBook.findAll", query = "Select b from ComicBook b"),
	@NamedQuery(name = "comicBook.findById", query = "Select b from ComicBook b where b.id = :id"),
	@NamedQuery(name = "comicBook.findByName", query = "Select b from ComicBook b where b.name = :name"),
	@NamedQuery(name= "comicBook.findComicBookPublishingHouses", query= "Select b.toyList from ComicBook b where b.id = :id")
})

public class ComicBook {

	private String title;
	private boolean isPopular;
	private double price;
	private Date  dateOfRelease;
	private int numberOfComics;
    private List<PublishingHouse> publishingHouseList = new ArrayList<PublishingHouse>();
    private List<Creator> creators = new ArrayList<Creator>();
    private Details details;
	
	
	public ComicBook() {
		super();
	}

	private Long id; 
	public ComicBook(String title, Date dateOfRelease, boolean isPopular, double price, int numberOfComics) {
		super();
		this.title = title;
		this.isPopular = isPopular;
		this.price = price;
		this.dateOfRelease = dateOfRelease;
		this.numberOfComics = numberOfComics;
	}

	public ComicBook(Long id, String title, Date dateOfRelease, boolean isPopular, double price, int numberOfComics) {
		super();
		this.id = id;
		this.title = title;
		this.isPopular = isPopular;
		this.price = price;
		this.dateOfRelease = dateOfRelease;
		this.numberOfComics = numberOfComics;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public ComicBook(String title) {
		super();
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean getIsPopular() {
		return isPopular;
	}
	public void setIsPopular(boolean isPopular) {
		this.isPopular = isPopular;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDateOfRelease() {
		return dateOfRelease;
	}
	public void setDateOfRelease(Date dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}
	
	public int getNumberOfComics() {
		return numberOfComics;
	}

	public void setNumberOfComics(int numberOfComics) {
		this.numberOfComics = numberOfComics;
	}
	
	//@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<PublishingHouse> getPublishingHouseList() {
        return publishingHouseList;
    }

    public void setPublishingHouseList(List<PublishingHouse> publishingHouseList) {
        this.publishingHouseList = publishingHouseList;
    }
	
    @OneToOne(fetch = FetchType.LAZY)
    public Details getDetails() {
        return details;
    }
    
    @ManyToMany
	public List<Creator> getCreators() {
		return creators;
	}
    
    public void setCreators(List<Creator> creators) {
		this.creators = creators;
	}

    public void setDetails(Details details) {
        this.details = details;
    }
	

	@Override
	public String toString() {
		return "ComicBook [title=" + title + ", isPopular=" + isPopular + ", price=" + price + ", dateOfRelease=" + dateOfRelease
				+ ", numberOfComics=" + numberOfComics + ", id=" + id + "]";
	}
	
}