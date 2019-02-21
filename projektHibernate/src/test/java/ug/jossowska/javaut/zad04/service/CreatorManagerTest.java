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

import ug.jossowska.javaut.zad04.domain.Creator;
import ug.jossowska.javaut.zad04.service.CreatorManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class CreatorManagerTest {
	
	final private String FIRSTNAME_1 = "Janek";
	final private String FIRSTNAME_2 = "Franek";
	
	final private String LASTNAME_1 = "Kowalski";
	final private String LASTNAME_2 = "Dolas";
	
	final private String FIRSTNAME_3 = "Robert";
	final private String LASTNAME_3 = "Bez";
	
	@Autowired
	CreatorManager manager;

	@Test
	public void addCreatorCheck()
	{
		Creator creator = new Creator();
		creator.setFirstName(FIRSTNAME_3);
		creator.setLastName(LASTNAME_3);
		manager.addCreator(creator);
		Creator ret = manager.getById(3L);
		assertEquals(FIRSTNAME_3, ret.getFirstName());
		assertEquals(LASTNAME_3, ret.getLastName());
	}
	
	@Test
	public void getCreatorByIdCheck()
	{
		Creator ret = manager.getById(2L);
		assertEquals(FIRSTNAME_2, ret.getFirstName());
		assertEquals(LASTNAME_2, ret.getLastName());
	}
	
	@Test
	public void getAllCreatorsCheck()
	{
		List<Creator> list = manager.getAll();
		assertEquals(2, list.size());
	}
	
	@Test
	public void deleteCreatorCheck()
	{
		List<Creator> list = manager.getAll();
		assertEquals(2, list.size());
		manager.deleteCreator(list.get(0));
		list = manager.getAll();
		assertEquals(1, list.size());
	}
	
	@Test
	public void updateCreatorCheck() {
		
		Creator ret = manager.getAll().get(0);
		ret.setFirstName("Hanna");
		manager.updateCreator(ret);
		assertEquals("Hanna", manager.getAll().get(0).getFirstName());
	}
	
	@Before
	public void fillDb()
	{
		Creator creator1 = new Creator();
		creator1.setFirstName(FIRSTNAME_1);
		creator1.setLastName(LASTNAME_1);
		manager.addCreator(creator1);
		
		Creator creator2 = new Creator();
		creator2.setFirstName(FIRSTNAME_2);
		creator2.setLastName(LASTNAME_2);
		manager.addCreator(creator2);
	}
	@After
	public void deleteAll(){
		manager.clearTable();
	}
}
