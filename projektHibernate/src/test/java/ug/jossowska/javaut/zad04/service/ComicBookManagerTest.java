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

import ug.jossowska.javaut.zad04.domain.ComicBook;
import ug.jossowska.javaut.zad04.domain.Creator;
import ug.jossowska.javaut.zad04.domain.Detail;
import ug.jossowska.javaut.zad04.domain.PublishingHouse;
import ug.jossowska.javaut.zad04.domain.Topic;
import ug.jossowska.javaut.zad04.service.ComicBookManager;
import ug.jossowska.javaut.zad04.service.CreatorManager;
import ug.jossowska.javaut.zad04.service.DetailManager;
import ug.jossowska.javaut.zad04.service.PublishingHouseManager;
import ug.jossowska.javaut.zad04.service.TopicManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ComicBookManagerTest {

	@Autowired
	ComicBookManager manager;
	@Autowired
	DetailManager detailmanager;
	@Autowired
	PublishingHouseManager publishingHousemanager;
	@Autowired
	CreatorManager creatormanager;
	@Autowired
	TopicManager topicmanager;
	
	final private String NAME_1 = "ComicBook111";
	final private String NAME_2 = "ComicBook122";
	final private String NAME_3 = "ComicBook123";
	final private String NAME_4 = "ComicBook114";
	final private double PRICE = 27.55;
	final private int NOV = 4;
	final private String DATE1 = "2015-05-22";
	final private String DATE2 = "2018-11-22";
	final private String DATE3 = "2017-10-21";
	
	final private String FIRSTNAME_1 = "Janek";
	final private String FIRSTNAME_2 = "Franek";
	
	final private String LASTNAME_1 = "Kowalski";
	final private String LASTNAME_2 = "Dolas";
	
	final private String FIRSTNAME_3 = "Robert";
	final private String LASTNAME_3 = "Bez";
	
	final private String DETAIL_NAME_1 = "glodny";
	final private String DETAIL_NAME_2 = "natchniony";
		
	final private String DETAIL_NAME_3 = "Label 3";
	
	final private String PUBLISHINGHOUSE_NAME_1 = "Marvel";
	final private String PUBLISHINGHOUSE_NAME_2 = "DC";
	
	final private String TOPIC_VALUE_1 = "Gosia";
	final private String TOPIC_VALUE_2 = "Zosia";
	final private String TOPIC_VALUE_3 = "Tosia";
	final private String TOPIC_VALUE_4 = "Romek";
	final private String TOPIC_VALUE_5 = "Tomek";
	final private String TOPIC_VALUE_6 = "Atomek";
	@Test
	public void addComicBookCheck()
	{
		ComicBook newComicBook = new ComicBook();
		newComicBook.setPrice(PRICE);
		newComicBook.setNov(NOV);
		newComicBook.setTitle(NAME_4);
		newComicBook.setDate(DATE1);
		manager.addComicBook(newComicBook);
		ComicBook comicBook = manager.getById(4L);
		assertEquals(NAME_4, comicBook.getTitle());
	}
	
	@Test
	public void getComicBookByIdCheck()
	{
		ComicBook retComicBook = manager.getById(1);
		assertEquals(NAME_1, retComicBook.getTitle());
	}
	
	@Test
	public void getAllComicBooksCheck()
	{
		List<ComicBook> list = manager.getAllComicBooks();
		assertEquals(3, list.size());
	}
	
	@Test
	public void updateComicBookCheck()
	{
		ComicBook comicBook = manager.getAllComicBooks().get(0);
		comicBook.setTitle("UPDATE");
		manager.updateComicBook(comicBook);
		ComicBook retcomicBook = manager.getAllComicBooks().get(0);
		assertEquals("UPDATE", retcomicBook.getTitle());
		
	}
	
	@Test
	public void deleteComicBookCheck()
	{
		ComicBook ret = manager.getById(1L);
		List<ComicBook> list = manager.getAllComicBooks();
		manager.deleteComicBook(ret);
		List<ComicBook> list2 = manager.getAllComicBooks();
		assertEquals(list.size()-1, list2.size());
	}
	
	@Test
	public void getAllCreatorsCheck()
	{
		ComicBook comicBook = manager.getAllComicBooks().get(0);
		List<Creator> list = manager.getAllCreators(comicBook.getId());
		assertEquals(comicBook.getCreators().size(), list.size());
	}
	
	@Test
	public void getAllComicBooksByPublishingHouseNameCheck()
	{
		List<ComicBook> list = manager.getAllComicBooksByPublishingHouseName(PUBLISHINGHOUSE_NAME_1);
		assertEquals(2, list.size());
	}
	
	@Test
	public void getAllComicBooksByDateBetweenNameCheck()
	{
		List<ComicBook> list = manager.getAllComicBooksWithDateBetween(DATE1, DATE2);
		assertEquals(3, list.size());
	}
	
	@Test
	public void getAllComicBooksCreatorsMoreThanCheck()
	{
		List<ComicBook> list = manager.getallComicBooksCreatorsMoreEqualThan(2);
		assertEquals(2, list.size());
	}
	@Test
	public void getallComicBooksNameMatchStringCheck()
	{
		List<ComicBook> list = manager.getallComicBooksTitleMatchString("12");
		assertEquals(2, list.size());
	}
	@Test
	public void deleteListOfComicBooks()
	{
		List<ComicBook> list = manager.getAllComicBooks();
		manager.deleteComicBooks(list);
		List<ComicBook> list2 = manager.getAllComicBooks();
		assertEquals(0, list2.size());
	}
		
	@Before
	public void fillDb()
	{
		Detail detail1 = new Detail();
		detail1.setDes(DETAIL_NAME_1);
		detailmanager.addDetail(detail1);
		Detail detail2 = new Detail();
		detail2.setDes(DETAIL_NAME_2);
		detailmanager.addDetail(detail2);
		Detail detail3 = new Detail();
		detail3.setDes(DETAIL_NAME_3);
		detailmanager.addDetail(detail3);
		
		PublishingHouse publishingHouse1 = new PublishingHouse();
		publishingHouse1.setName(PUBLISHINGHOUSE_NAME_1);
		publishingHousemanager.addPublishingHouse(publishingHouse1);
		PublishingHouse publishingHouse2 = new PublishingHouse();
		publishingHouse2.setName(PUBLISHINGHOUSE_NAME_2);
		publishingHousemanager.addPublishingHouse(publishingHouse2);
		
		Creator creator1 = new Creator();
		creator1.setFirstName(FIRSTNAME_1);
		creator1.setLastName(LASTNAME_1);
		creatormanager.addCreator(creator1);
		
		Creator creator2 = new Creator();
		creator2.setFirstName(FIRSTNAME_2);
		creator2.setLastName(LASTNAME_2);
		creatormanager.addCreator(creator2);
		
		Creator creator3 = new Creator();
		creator3.setFirstName(FIRSTNAME_3);
		creator3.setLastName(LASTNAME_3);
		creatormanager.addCreator(creator3);
		
		Topic topic1 = new Topic(TOPIC_VALUE_1);
		Topic topic2 = new Topic(TOPIC_VALUE_2);
		Topic topic3 = new Topic(TOPIC_VALUE_3);
		topicmanager.addTopic(topic3);
		topicmanager.addTopic(topic2);
		topicmanager.addTopic(topic1);
		
		Topic topic4 = new Topic(TOPIC_VALUE_4);
		Topic topic5 = new Topic(TOPIC_VALUE_5);
		Topic topic6 = new Topic(TOPIC_VALUE_6);
		topicmanager.addTopic(topic4);
		topicmanager.addTopic(topic5);
		topicmanager.addTopic(topic6);
		
		ComicBook comicBook1 = new ComicBook();
		comicBook1.setNov(NOV);
		comicBook1.setPrice(PRICE);
		comicBook1.setTitle(NAME_1);
		comicBook1.setDate(DATE1);
		comicBook1.setDetail(detail1);
		comicBook1.setPublishingHouse(publishingHouse1);
		comicBook1.getCreators().add(creator1);
		comicBook1.getCreators().add(creator2);
		comicBook1.getTopics().add(topic1);
		comicBook1.getTopics().add(topic2);
		manager.addComicBook(comicBook1);
		ComicBook comicBook2 = new ComicBook();
		comicBook2.setNov(NOV);
		comicBook2.setPrice(PRICE);
		comicBook2.setTitle(NAME_2);
		comicBook2.setDate(DATE2);
		comicBook2.setDetail(detail2);
		comicBook2.setPublishingHouse(publishingHouse1);
		comicBook2.getCreators().add(creator1);
		comicBook2.getTopics().add(topic3);
		comicBook2.getTopics().add(topic4);
		manager.addComicBook(comicBook2);
		ComicBook comicBook3 = new ComicBook();
		comicBook3.setNov(NOV);
		comicBook3.setPrice(PRICE);
		comicBook3.setTitle(NAME_3);
		comicBook3.setDate(DATE3);
		comicBook3.setDetail(detail3);
		comicBook3.setPublishingHouse(publishingHouse2);
		comicBook3.getCreators().add(creator3);
		comicBook3.getCreators().add(creator1);
		comicBook3.getTopics().add(topic5);
		comicBook3.getTopics().add(topic6);
		manager.addComicBook(comicBook3);
	}
	
	@After
	public void deleteAll(){
		manager.clearTable();
	}
}
