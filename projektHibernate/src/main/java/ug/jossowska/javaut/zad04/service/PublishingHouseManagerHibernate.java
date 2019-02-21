package ug.jossowska.javaut.zad04.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.jossowska.javaut.zad04.domain.PublishingHouse;

@Component
@Transactional
public class PublishingHouseManagerHibernate implements PublishingHouseManager{

	@Autowired
	SessionFactory session;
	
	@Override
	public void addPublishingHouse(PublishingHouse publishingHouse) {
		session.getCurrentSession().persist(publishingHouse);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PublishingHouse> getAllPublishingHouses() {
		return session.getCurrentSession().getNamedQuery("publishingHouse.all").list();
	}

	@Override
	public PublishingHouse getPublishingHouseById(long id) {
		return (PublishingHouse) session.getCurrentSession().get(PublishingHouse.class, id);
	}

	@Override
	public void deletePublishingHouse(PublishingHouse publishingHouse) {
		session.getCurrentSession().delete(publishingHouse);
		
	}

	@Override
	public void updatePublishingHouse(PublishingHouse publishingHouse) {
		session.getCurrentSession().saveOrUpdate(publishingHouse);
	}

	@Override
	public PublishingHouse getPublishingHouseByName(String name) {
		return (PublishingHouse) session.getCurrentSession().getNamedQuery("publishingHouse.name").setString("name", name).uniqueResult();
	}
	
	public void clearTable()
	{
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE PUBLISHINGHOUSE RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
	}
}
