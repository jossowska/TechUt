package ug.jossowska.javaut.zad04.domain;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
	@NamedQuery(name="comicBook.all",query="select w from ComicBook w"),
	@NamedQuery(name="comicBook.getAllCreators",query="select w.creators from ComicBook w where w.id = :id"),
	@NamedQuery(name="comicBook.allComicBooksByPublishingHouse",query="select w from ComicBook w JOIN w.publishingHouse mm where mm.name = :name"),
	@NamedQuery(name="comicBook.allComicBooksByDateBetween",query="select w from ComicBook w where w.date between :date1  and :date2"),
	@NamedQuery(name="comicBook.allComicBooksCreatorsMoreEqualThan",query="select w from ComicBook w JOIN w.creators e group by w.id having count(e.id) >= :number"),
	@NamedQuery(name="comicBook.allComicBooksTitleMatchString",query="select w from ComicBook w where w.title LIKE :search")
})
public class ComicBook {
	private long id;
	private String title;
	private int nov;
	private double price;
	private Date date;
	private PublishingHouse publishingHouse;
	private Detail detail;
	private Set<Creator> creators = new HashSet<Creator>();
	private Set<Topic> topics = new HashSet<Topic>();
	
	public ComicBook() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNov() {
		return nov;
	}
	public void setNov(int nov) {
		this.nov = nov;
	}
	public Date getDate() {
		return date;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setDate(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.date=(df.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setDate(Date date) {
		this.date=date;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	public PublishingHouse getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(PublishingHouse publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	public Set<Creator> getCreators() {
		return creators;
	}
	public void setCreators(Set<Creator> creators) {
		this.creators = creators;
	}
	
	@OneToMany(fetch=FetchType.EAGER)
	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

}
