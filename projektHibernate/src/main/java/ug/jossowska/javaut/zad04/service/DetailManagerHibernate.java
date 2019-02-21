package ug.jossowska.javaut.zad04.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.jossowska.javaut.zad04.domain.Detail;
@Component
@Transactional
public class DetailManagerHibernate implements DetailManager {
	
	@Autowired
	SessionFactory session;
	
	@Override
	public void addDetail(Detail detail) {
		session.getCurrentSession().persist(detail);
		
	}

	@Override
	public Detail getById(long id) {
		return (Detail) session.getCurrentSession().get(Detail.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Detail> getAll() {
		return session.getCurrentSession().getNamedQuery("detail.all").list();
	}

	@Override
	public void deleteDetail(Detail detail) {
		session.getCurrentSession().delete(detail);
		
	}

	@Override
	public void updateDetail(Detail detail) {
		session.getCurrentSession().saveOrUpdate(detail);
		
	}
	
	public void clearTable()
	{
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE DETAIL RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
	}
}
