package ug.jossowska.techut.zad05.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.jossowska.techut.zad05.domain.ComicBook;
import ug.jossowska.techut.zad05.domain.Creator;
import ug.jossowska.techut.zad05.domain.Details;
import ug.jossowska.techut.zad05.domain.PublishingHouse;


@Component
@Transactional
public class ComicBookManagerHibernate implements ComicBookManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addComicBook(ComicBook b) {
		//b.setId(null);
		//sessionFactory.getCurrentSession().persist(b);
		sessionFactory.getCurrentSession().save(b);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<ComicBook> getAllComics() {
        return sessionFactory.getCurrentSession().getNamedQuery("comicBook.findAll").list();
	}

	@Override
	public ComicBook findComicBookByName(String name) {
        return (ComicBook) sessionFactory.getCurrentSession().get(ComicBook.class, name);
	}

	@Override
	public ComicBook findComicBookById(Long id) {
        return (ComicBook) sessionFactory.getCurrentSession().get(ComicBook.class, id);
	}

	@Override
	public void addCreator(Creator creator) {
		sessionFactory.getCurrentSession().save(creator);
		
	}

	@Override
	public void deleteCreator(Creator creator) {
        creator = (Creator) sessionFactory.getCurrentSession().get(Creator.class,
                creator.getId());
        sessionFactory.getCurrentSession().delete(creator);
	}

	@Override
	public Creator findCreatorById(Long id) {
        return (Creator) sessionFactory.getCurrentSession().get(Creator.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Creator> getAllCreators() {
        return sessionFactory.getCurrentSession().getNamedQuery("creator.findAll").list();
	}


	@Override
	public void addPublishingHouse(PublishingHouse publishingHouse) {
		sessionFactory.getCurrentSession().save(publishingHouse);
		
	}

	@Override
	public void deletePublishingHouse(PublishingHouse publishingHouse) {
        publishingHouse = (PublishingHouse) sessionFactory.getCurrentSession().get(PublishingHouse.class,
                publishingHouse.getId());
        sessionFactory.getCurrentSession().delete(publishingHouse);
		
	}

	@Override
	public PublishingHouse findPublishingHouseById(Long id) {
        return (PublishingHouse) sessionFactory.getCurrentSession().get(PublishingHouse.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PublishingHouse> getAllPublishingHouses() {
        return sessionFactory.getCurrentSession().getNamedQuery("publishingHouse.findAll").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PublishingHouse> findComicBookPublishingHouses(ComicBook comicBook) {
        return sessionFactory.getCurrentSession().getNamedQuery("comicBook.findComicBookPublishingHouses").setLong("id", comicBook.getId()).list();
	}

	@Override
	public void addDetails(Details details) {
		sessionFactory.getCurrentSession().save(details);
		
	}

	@Override
	public void deleteDetails(Details details) {
        details = (Details) sessionFactory.getCurrentSession().get(Details.class,
                details.getId());
        sessionFactory.getCurrentSession().delete(details);
		
	}

	@Override
	public Details findDetailsById(Long id) {
        return (Details) sessionFactory.getCurrentSession().get(Details.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Details> getAllDetails() {
        return sessionFactory.getCurrentSession().getNamedQuery("details.findAll").list();
	}


	@Override
	public List<ComicBook> getCreatorComics(Creator creator) {
        creator = (Creator) sessionFactory.getCurrentSession().get(Creator.class,
                creator.getId());
        List<ComicBook> comics = new ArrayList<ComicBook>(creator.getComicBookList());
        return comics;
	}

	@Override
	public void deleteComicBook(ComicBook comicBook) {
        comicBook = (ComicBook) sessionFactory.getCurrentSession().get(ComicBook.class,
                comicBook.getId());
        sessionFactory.getCurrentSession().delete(comicBook);
		
	}

	@Override
	public void addComicBookToCreator(ComicBook b, Creator o) {
        List<ComicBook> comics = o.getComicBookList();
        comics.add(b);
        o.setComicBookList(comics);
        sessionFactory.getCurrentSession().save(o);
		
	}

	@Override
	public void removeComicBookFromCreator(ComicBook b, Creator o) {
        List<ComicBook> comics = o.getComicBookList();
        comics.remove(b);
        o.setComicBookList(comics);
        sessionFactory.getCurrentSession().save(o);
		
	}

	@Override
	public void addPublishingHouseToComicBook(PublishingHouse t, ComicBook b) {
        List<PublishingHouse> publishingHouses = b.getPublishingHouseList();
        publishingHouses.add(t);
        b.setPublishingHouseList(publishingHouses);
        sessionFactory.getCurrentSession().save(b);
		
	}
	
}
