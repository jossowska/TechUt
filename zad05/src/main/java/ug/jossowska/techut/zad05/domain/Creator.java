package ug.jossowska.techut.zad05.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "creator.findAll", query = "Select o from Creator o"),
	@NamedQuery(name = "creator.findById", query = "Select o from Creator o where o.id = :id")
})
public class Creator {

    private Long id;
    private String name;

    private List<ComicBook> comicBookList = new ArrayList<ComicBook>();

    public Creator() {
		super();
	}
    
    public Creator(String name) {
		super();
		this.name = name;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.name = firstName;
    }

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToMany
    public List<ComicBook> getComicBookList() {
        return comicBookList;
    }

    public void setComicBookList(List<ComicBook> comicBookList) {
        this.comicBookList = comicBookList;
    }

	
    
}