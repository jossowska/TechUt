package ug.jossowska.javaut.zad04.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.jossowska.javaut.zad04.domain.Creator;
@Component
@Transactional
public class CreatorManagerHibernate implements CreatorManager {
	
	@Autowired
	SessionFactory session;
	
	@Override
	public void addCreator(Creator creator) {
		session.getCurrentSession().persist(creator);
		
	}

	@Override
	public Creator getById(long id) {
		return (Creator) session.getCurrentSession().get(Creator.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Creator> getAll() {
		return session.getCurrentSession().getNamedQuery("creator.all").list();
	}

	@Override
	public void deleteCreator(Creator creator) {
		session.getCurrentSession().delete(creator);
		
	}

	@Override
	public void updateCreator(Creator creator) {
		session.getCurrentSession().saveOrUpdate(creator);
		
	}
	
	public void clearTable()
	{
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE CREATOR RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
	}
}
