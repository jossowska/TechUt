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

import ug.jossowska.javaut.zad04.domain.Detail;
import ug.jossowska.javaut.zad04.service.DetailManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class DetailManagerTest {
	
	final private String NAME_1 = "xhjgki";
	final private String NAME_2 = "poprawnyniepoprawny";
	final private String NAME_3 = "autorjestglodny";

	
	@Autowired
	DetailManager manager;

	@Test
	public void addDetailCheck()
	{
		Detail detail = new Detail();
		detail.setDes(NAME_3);
		manager.addDetail(detail);
		Detail ret = manager.getById(3L);
		assertEquals(NAME_3, ret.getDes());
	}
	
	@Test
	public void getDetailByIdCheck()
	{
		Detail ret = manager.getById(2L);
		assertEquals(NAME_2, ret.getDes());
	}
	
	@Test
	public void getAllDetailCheck()
	{
		List<Detail> list = manager.getAll();
		assertEquals(2, list.size());
	}
	
	@Test
	public void deleteDetailCheck()
	{
		List<Detail> list = manager.getAll();
		assertEquals(2, list.size());
		manager.deleteDetail(list.get(0));
		list = manager.getAll();
		assertEquals(1, list.size());
	}
	
	@Test
	public void updateDetailCheck() {
		
		Detail ret = manager.getAll().get(0);
		ret.setDes("Detail Update");
		manager.updateDetail(ret);
		assertEquals("Detail Update", manager.getAll().get(0).getDes());
	}
	
	@Before
	public void fillDb()
	{
		Detail detail1 = new Detail();
		detail1.setDes(NAME_1);
		manager.addDetail(detail1);
		
		Detail detail2 = new Detail();
		detail2.setDes(NAME_2);
		manager.addDetail(detail2);
	}
	@After
	public void deleteAll(){
		manager.clearTable();
	}
}
