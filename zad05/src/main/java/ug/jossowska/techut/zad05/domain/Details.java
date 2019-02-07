package ug.jossowska.techut.zad05.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@NamedQueries({
	@NamedQuery(name = "details.findAll", query = "Select t from Details t"),
	@NamedQuery(name = "details.findById", query = "Select t from Details t where t.id = :id")
})
@Entity
public class Details {

    private Long id;
    private String name;
    private String description;

    public Details(String name, String descr) {
		this.name = name;
		this.description = descr;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
