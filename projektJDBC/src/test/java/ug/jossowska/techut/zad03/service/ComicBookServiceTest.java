package ug.jossowska.techut.zad03.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ug.jossowska.techut.zad03.domain.ComicBook;


@SuppressWarnings("deprecation")
public class ComicBookServiceTest {
	ComicBookServiceJDBC service = new ComicBookServiceJDBC();
	
	private final String COMICBOOK1_TITLE = "League of Justice";
	private final boolean COMICBOOK1_ISPOPULAR = true;
	private final double COMICBOOK1_PRICE = 33.99;
	private final Date COMICBOOK1_DATEOFRELEASE = new Date(1999,02,12);
	
	private final String COMICBOOK2_TITLE = "Nordic Mysticism";
	private final boolean COMICBOOK2_ISPOPULAR = false;
	private final double COMICBOOK2_PRICE = 5.99;
	private final Date COMICBOOK2_DATEOFRELEASE = new Date(2012,05,22);
	
	private final String COMICBOOK3_TITLE = "Batman";
	private final boolean COMICBOOK3_ISPOPULAR = true;
	private final double COMICBOOK3_PRICE = 25.99;
	private final Date COMICBOOK3_DATEOFRELEASE = new Date(2002,01,30);
	
	private final String COMICBOOK4_TITLE = "Wings";
	private final boolean COMICBOOK4_ISPOPULAR = false;
	private final double COMICBOOK4_PRICE = 8.99;
	private final Date COMICBOOK4_DATEOFRELEASE = new Date(2012,05,17);
	
	@Test
	public void checkConnection() {
		assertNotNull(service.getConnection());
	}
	
	@Test
	public void checkAddComicBook() {
		ComicBook comicBook4 = new ComicBook(COMICBOOK4_TITLE, COMICBOOK4_DATEOFRELEASE, COMICBOOK4_ISPOPULAR, COMICBOOK4_PRICE);
		service.addComicBook(comicBook4);
		ComicBook retComicBook = service.getComicBookById(service.getComicBookByTitle(COMICBOOK4_TITLE).getId());
		assertEquals(COMICBOOK4_TITLE, retComicBook.getTitle());
		assertEquals(COMICBOOK4_PRICE, retComicBook.getPrice(),0);
		assertEquals(COMICBOOK4_ISPOPULAR, retComicBook.getIsPopular());
		assertEquals(COMICBOOK4_DATEOFRELEASE, retComicBook.getDateOfRelease());
		
	}
	
	@Test
	public void checkAddAllComics()
	{
		
		List<ComicBook> comicBookList = new ArrayList<ComicBook>();
		
		comicBookList.add(new ComicBook("COMICBOOK1",COMICBOOK1_DATEOFRELEASE, COMICBOOK1_ISPOPULAR, COMICBOOK1_PRICE));
		
		comicBookList.add(new ComicBook("COMICBOOK2", COMICBOOK1_DATEOFRELEASE, COMICBOOK1_ISPOPULAR, COMICBOOK1_PRICE));
	
		service.addAllComics(comicBookList);

		int size = service.getAllComics().size();
		assertEquals(5, size);
	
	}
	
	@Test
	public void checkGetComicBookByTitle()
	{
		ComicBook retProcessor = service.getComicBookByTitle(COMICBOOK2_TITLE);
		assertEquals(COMICBOOK2_TITLE, retProcessor.getTitle());
	}
	
	@Test
	public void checkDeleteAllComics()
	{
		service.deleteAllComics();
		int size = service.getAllComics().size();
		assertEquals(0, size);
	}
	
	@Test
	public void checkGetAllPopularOrUnpopularComics() {
		List<ComicBook> retProcessorList = service.getAllPopularOrUnpopularComics(true);
		assertEquals(2, retProcessorList.size());
	}
	
	@Test
	public void checkDeleteById() {
		ComicBook retComicBook = service.getComicBookByTitle(COMICBOOK2_TITLE);
		service.deleteComicBookById(retComicBook.getId());

		List<ComicBook> comicBookList = service.getAllComics();
		assertEquals(2, comicBookList.size());
		for(ComicBook comicBook : comicBookList)
		{
			assertNotEquals(retComicBook.getId(), comicBook.getId());
		}
	}
	
	@Before
	public void fillDb()
	{
		service.deleteAllComics();
		ComicBook comicBook1 = new ComicBook(COMICBOOK1_TITLE, COMICBOOK1_DATEOFRELEASE, COMICBOOK1_ISPOPULAR, COMICBOOK1_PRICE);
		ComicBook comicBook2 = new ComicBook(COMICBOOK2_TITLE, COMICBOOK2_DATEOFRELEASE, COMICBOOK2_ISPOPULAR, COMICBOOK2_PRICE);
		ComicBook comicBook3 = new ComicBook(COMICBOOK3_TITLE, COMICBOOK3_DATEOFRELEASE, COMICBOOK3_ISPOPULAR, COMICBOOK3_PRICE);

		service.addComicBook(comicBook1);
		service.addComicBook(comicBook2);
		service.addComicBook(comicBook3);
	}
	
	
	
}
