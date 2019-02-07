package ug.jossowska.techut.zad05.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


import ug.jossowska.techut.zad05.domain.ComicBook;
import ug.jossowska.techut.zad05.domain.Creator;
import ug.jossowska.techut.zad05.domain.Details;
import ug.jossowska.techut.zad05.domain.PublishingHouse;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class ComicBookManagerTest {

	@Autowired
	ComicBookManager bm;

	private final String NAME_PUBLISHING_HOUSE_1 = "Blue ball";
	private final String NAME_PUBLISHING_HOUSE_2 = "Bambol";
	private final String NAME_PUBLISHING_HOUSE_3 = "Nunchaku";
	
	private final String NAME_DETAILS_1 = "GOD";
	private final String NAME_DETAILS_2 = "YES";
	private final String NAME_DETAILS_3 = "SLEEP";
	private final String DESCRIPTION_DETAILS_1 = "GODANDBAD";
	private final String DESCRIPTION_DETAILS_2 = "YESANDNO";
	private final String DESCRIPTION_DETAILS_3 = "YESPLEASE";
	
	private final String NAME_CREATOR_1 = "Dryante";
	private final String NAME_CREATOR_2 = "Delain";
	private final String NAME_CREATOR_3 = "Danifae";
	
	private final String TITLE_COMICBOOK_1 = "Aquaman";
	private final String TITLE_COMICBOOK_2 = "Batman";
	private final String TITLE_COMICBOOK_3 = "Justice League";
	private final double PRICE_COMICBOOK_1 = 12.5;
	private final double PRICE_COMICBOOK_2 = 10.0;
	private final double PRICE_COMICBOOK_3 = 8.5;
	private final Date DATE_COMICBOOK_1 = new Date(2017, 12, 14);
	private final Date DATE_COMICBOOK_2 = new Date(2000, 12, 14);
	private final Date DATE_COMICBOOK_3 = new Date(2005, 12, 14);
	private final int COMICS_COMICBOOK_1 = 5;
	private final int COMICS_COMICBOOK_2 = 7;
	private final int COMICS_COMICBOOK_3 = 4;
	
	
	
	//____________________________________________________________________________PUBLISHING_HOUSES TESTS:
	@Test
	public void addPublishingHousesTest() {
		PublishingHouse t = new PublishingHouse(NAME_PUBLISHING_HOUSE_1);
		bm.addPublishingHouse(t);
		PublishingHouse retrievedPublishingHouse = bm.findPublishingHouseById(t.getId());
		assertEquals(NAME_PUBLISHING_HOUSE_1, retrievedPublishingHouse.getName());
	}
    
	@Test
	public void getAllPublishingHousesTest() {
		PublishingHouse t1 = new PublishingHouse(NAME_PUBLISHING_HOUSE_1);
		PublishingHouse t2 = new PublishingHouse(NAME_PUBLISHING_HOUSE_2);
		PublishingHouse t3 = new PublishingHouse(NAME_PUBLISHING_HOUSE_3);
		bm.addPublishingHouse(t1);
		bm.addPublishingHouse(t2);
		bm.addPublishingHouse(t3);
		assertEquals(3, bm.getAllPublishingHouses().size());
	}
	
//	@Test
//	public void deletePublishingHouseTest() {
//		PublishingHouse t1 = new PublishingHouse(NAME_PUBLISHING_HOUSE_1);
//		bm.addPublishingHouse(t1);
//		int count = bm.getAllPublishingHouses().size();
//		bm.deletePublishingHouse(t1);
//		assertEquals(count-1, bm.getAllPublishingHouses().size());
//	}
	
//	//_______________________________________________________________________________DETAILS TESTS:
//	
//
//	@Test
//	public void addDetailsTest() {
//		Details t = new Details(NAME_DETAILS_1, DESCRIPTION_DETAILS_1);
//		bm.addDetails(t);
//		Details retrievedDetails = bm.findDetailsById(t.getId());
//		assertEquals(NAME_DETAILS_1, retrievedDetails.getName());
//	}
//    
//	@Test
//	public void getAllDetailsTest() {
//		Details t1 = new Details(NAME_DETAILS_1, DESCRIPTION_DETAILS_1);
//		Details t2 = new Details(NAME_DETAILS_2, DESCRIPTION_DETAILS_2);
//		Details t3 = new Details(NAME_DETAILS_3, DESCRIPTION_DETAILS_3);
//		bm.addDetails(t1);
//		bm.addDetails(t2);
//		bm.addDetails(t3);
//		assertEquals(3, bm.getAllDetails().size());
//	}
//	
//	@Test
//	public void deleteTailTest() {
//		Details t = new Details(NAME_DETAILS_1, DESCRIPTION_DETAILS_1);
//		bm.addDetails(t);
//		int count = bm.getAllDetails().size();
//		bm.deleteDetails(t);
//		assertEquals(count-1, bm.getAllDetails().size());}
	
	//____________________________________________________________________________________CREATOR TESTS:
	
//	@Test
//	public void addCreatorTest() {
//		Creator o = new Creator(NAME_CREATOR_1);
//		bm.addCreator(o);
//		Creator retrievedCreator = bm.findCreatorById(o.getId());
//		assertEquals(NAME_CREATOR_1, retrievedCreator.getFirstName());
//	}
//
//	@Test
//	public void getAllCreatorsTest() {
//		Creator o1 = new Creator(NAME_CREATOR_1);
//		Creator o2 = new Creator(NAME_CREATOR_2);
//		Creator o3 = new Creator(NAME_CREATOR_3);
//		bm.addCreator(o1);
//		bm.addCreator(o2);
//		bm.addCreator(o3);
//		assertEquals(3, bm.getAllCreators().size());
//	}
//
//	
//	
	

	@Test
	public void addComicBookToOwnerTest(){
		ComicBook b = new ComicBook(TITLE_COMICBOOK_1, DATE_COMICBOOK_1, true, PRICE_COMICBOOK_1, COMICS_COMICBOOK_1);
		bm.addComicBook(b);
		Creator o = new Creator(NAME_CREATOR_1);
		bm.addCreator(o);
		int count = bm.getCreatorComics(o).size();
		bm.addComicBookToCreator(b, o);
		assertEquals(count+1, bm.getCreatorComics(o).size());
	}
	
	


	@Test
	public void findComicBookPublishingHouseTest(){ 
		ComicBook b = new ComicBook(TITLE_COMICBOOK_1, DATE_COMICBOOK_1, true, PRICE_COMICBOOK_1, COMICS_COMICBOOK_1);
		bm.addComicBook(b);

		PublishingHouse t1 = new PublishingHouse(NAME_PUBLISHING_HOUSE_1);
		PublishingHouse t2 = new PublishingHouse(NAME_PUBLISHING_HOUSE_2);
		PublishingHouse t3 = new PublishingHouse(NAME_PUBLISHING_HOUSE_3);
		bm.addPublishingHouse(t1);
		bm.addPublishingHouse(t2);
		bm.addPublishingHouse(t3);

		bm.addPublishingHouseToComicBook(t1, b);
		bm.addPublishingHouseToComicBook(t2, b);
		bm.addPublishingHouseToComicBook(t3, b);

		int count = bm.findComicBookPublishingHouses(b).size();

		assertEquals(1, count);
	}
	
	
}