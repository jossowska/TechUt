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

import ug.jossowska.javaut.zad04.domain.PublishingHouse;
import ug.jossowska.javaut.zad04.service.PublishingHouseManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class PublishingHouseManagerTest {
	
	final private String NAME_1 = "Marvel";
	final private String NAME_2 = "DC";
	final private String NAME_3 = "Miths";
	final private String NAME_4 = "CI";
	@Autowired
	PublishingHouseManager manager;

	@Test
	public void addPublishingHouseCheck()
	{
		PublishingHouse publishingHouse = new PublishingHouse();
		publishingHouse.setName(NAME_3);
		manager.addPublishingHouse(publishingHouse);
		PublishingHouse ret = manager.getPublishingHouseById(3L);
		assertEquals(NAME_3, ret.getName());
	}
	
	@Test
	public void getPublishingHouseByIdCheck()
	{
		PublishingHouse retPublishingHouse = manager.getPublishingHouseById(2L);
		assertEquals(NAME_2, retPublishingHouse.getName());
	}
	
	@Test
	public void getAllPublishingHousesCheck()
	{
		List<PublishingHouse> list = manager.getAllPublishingHouses();
		assertEquals(2, list.size());
	}
	
	@Test
	public void deletePublishingHouseCheck()
	{
		List<PublishingHouse> list = manager.getAllPublishingHouses();
		assertEquals(2, list.size());
		manager.deletePublishingHouse(list.get(0));
		list = manager.getAllPublishingHouses();
		assertEquals(1, list.size());
	}
	
	@Test
	public void updatePublishingHouseCheck() {
		
		PublishingHouse retPublishingHouse = manager.getPublishingHouseByName(NAME_2);
		retPublishingHouse.setName(NAME_4);
		manager.updatePublishingHouse(retPublishingHouse);
		assertEquals(NAME_4, manager.getPublishingHouseByName(NAME_4).getName());
	}
	
	@Test
	public void getPublishingHouseByNameCheck()
	{
		PublishingHouse retPublishingHouse = manager.getPublishingHouseByName(NAME_1);
		assertEquals(NAME_1, retPublishingHouse.getName());
	}
	
	@Before
	public void fillDb()
	{
		PublishingHouse publishingHouse = new PublishingHouse();
		publishingHouse.setName(NAME_1);
		manager.addPublishingHouse(publishingHouse);
		PublishingHouse publishingHouse2 = new PublishingHouse();
		publishingHouse2.setName(NAME_2);
		manager.addPublishingHouse(publishingHouse2);
	}
	@After
	public void deleteAll(){
		manager.clearTable();
	}
}
