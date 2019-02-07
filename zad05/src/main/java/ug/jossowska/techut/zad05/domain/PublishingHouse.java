package ug.jossowska.techut.zad05.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



@NamedQueries({
		@NamedQuery(name = "publishingHouse.findAll", query = "Select t from PublishingHouse t"),
		@NamedQuery(name = "publishingHouse.findById", query = "Select t from PublishingHouse t where t.id = :id")
})
@Entity
public class PublishingHouse {

    private Long id;
    private String name;
    @ManyToOne
    private ComicBook comicBook;
    public PublishingHouse(String name) {
		this.name = name;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}