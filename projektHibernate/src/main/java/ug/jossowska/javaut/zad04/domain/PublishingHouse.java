package ug.jossowska.javaut.zad04.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="publishingHouse.all",query="Select w from PublishingHouse w"),
	@NamedQuery(name="publishingHouse.name",query="Select w from PublishingHouse w where w.name = :name")
})
public class PublishingHouse {
	private long id;
	private String name;
	
	public PublishingHouse() {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PublishingHouse(String name) {
		super();
		this.name = name;
	}
	
}
