package ug.jossowska.javaut.zad04.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.jossowska.javaut.zad04.domain.ComicBook;
import ug.jossowska.javaut.zad04.domain.Creator;
@Component
@Transactional
public class ComicBookManagerHibernate implements ComicBookManager{

	@Autowired
	SessionFactory session;
	
	@Override
	public void addComicBook(ComicBook comicBook) {
		session.getCurrentSession().persist(comicBook);
	}

	@Override
	public ComicBook getById(long id) {
		return (ComicBook) session.getCurrentSession().get(ComicBook.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComicBook> getAllComicBooks() {
		return (List<ComicBook>) session.getCurrentSession().getNamedQuery("comicBook.all").list();
	}

	@Override
	public void deleteComicBook(ComicBook comicBook) {
		session.getCurrentSession().delete(comicBook);
	}

	@Override
	public void updateComicBook(ComicBook comicBook) {
		session.getCurrentSession().saveOrUpdate(comicBook);
	}
	public void clearTable()
	{
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE CREATOR RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE DETAIL RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE PUBLISHINGHOUSE RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE COMICBOOK_CREATOR RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE TOPIC RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE COMICBOOK_TOPIC RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE COMICBOOK RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComicBook> getAllComicBooksByPublishingHouseName(String name) {
		return (List<ComicBook>) session.getCurrentSession().getNamedQuery("comicBook.allComicBooksByPublishingHouse").setString("name", name).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Creator> getAllCreators(long id) {
		return (List<Creator>) session.getCurrentSession().getNamedQuery("comicBook.getAllCreators").setLong("id", id).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ComicBook> getallComicBooksCreatorsMoreEqualThan(int number) {
		return (List<ComicBook>) session.getCurrentSession().getNamedQuery("comicBook.allComicBooksCreatorsMoreEqualThan").setInteger("number", number).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ComicBook> getallComicBooksTitleMatchString(String match) {
		return (List<ComicBook>) session.getCurrentSession().getNamedQuery("comicBook.allComicBooksTitleMatchString").setString("search", "%"+match+"%").list();
	}

	@Override
	public void deleteComicBooks(List<ComicBook> comicBooks) {
		for(ComicBook ret : comicBooks)
			session.getCurrentSession().delete(ret);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComicBook> getAllComicBooksWithDateBetween(String date1, String date2) {
		Date date1p = null;
		Date date2p = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date1p=(df.parse(date1));
			date2p=(df.parse(date2));
			return (List<ComicBook>) session.getCurrentSession().getNamedQuery("comicBook.allComicBooksByDateBetween").setDate("date1", date1p).setDate("date2", date2p).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
