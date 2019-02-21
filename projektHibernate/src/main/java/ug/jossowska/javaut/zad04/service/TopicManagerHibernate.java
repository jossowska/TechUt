package ug.jossowska.javaut.zad04.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.jossowska.javaut.zad04.domain.Topic;
@Component
@Transactional
public class TopicManagerHibernate implements TopicManager {
	
	@Autowired
	SessionFactory session;
	
	@Override
	public void addTopic(Topic topic) {
		session.getCurrentSession().persist(topic);
	
	}

	@Override
	public Topic getById(long id) {
		return (Topic) session.getCurrentSession().get(Topic.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAll() {
		return session.getCurrentSession().getNamedQuery("topic.all").list();
	}

	@Override
	public void deleteTopic(Topic topic) {
		session.getCurrentSession().delete(topic);
		
	}

	@Override
	public void updateTopic(Topic topic) {
		session.getCurrentSession().saveOrUpdate(topic);
		
	}
	
	public void clearTable()
	{
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE TOPIC RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
	}
}
