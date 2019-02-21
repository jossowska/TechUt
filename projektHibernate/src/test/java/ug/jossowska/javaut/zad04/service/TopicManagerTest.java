package ug.jossowska.javaut.zad04.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ug.jossowska.javaut.zad04.domain.Topic;
import ug.jossowska.javaut.zad04.service.TopicManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class TopicManagerTest {
	
	final private String VALUE_1 = "TAK";
	final private String VALUE_2 = "NIE";
	final private String VALUE_3 = "MEJBI";
	
	@Autowired
	TopicManager manager;

	@Test
	public void addTopicCheck()
	{
		Topic topic = new Topic();
		topic.setName(VALUE_3);
		manager.addTopic(topic);
		Topic ret = manager.getById(3L);
		assertEquals(VALUE_3, ret.getName());
	}
	
	@Test
	public void getTopicByIdCheck()
	{
		Topic ret = manager.getById(2L);
		assertEquals(VALUE_2, ret.getName());
	}
	
	@Test
	public void getAllTopicsCheck()
	{
		List<Topic> list = manager.getAll();
		assertEquals(2, list.size());
	}
	
	@Test
	public void deleteTopicCheck()
	{
		List<Topic> list = manager.getAll();
		assertEquals(2, list.size());
		manager.deleteTopic(list.get(0));
		list = manager.getAll();
		assertEquals(1, list.size());
	}
	
	@Test
	public void updateTopicCheck() {
		
		Topic ret = manager.getAll().get(0);
		ret.setName("Topic Update");
		manager.updateTopic(ret);
		assertEquals("Topic Update", manager.getAll().get(0).getName());
	}
	
	@Before
	public void fillDb()
	{
		Topic topic1 = new Topic();
		topic1.setName(VALUE_1);
		manager.addTopic(topic1);
		
		Topic topic2 = new Topic();
		topic2.setName(VALUE_2);
		manager.addTopic(topic2);
	}
	@After
	public void deleteAll(){
		manager.clearTable();
	}
}
