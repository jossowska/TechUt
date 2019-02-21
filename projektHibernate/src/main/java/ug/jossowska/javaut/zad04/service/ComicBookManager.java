package ug.jossowska.javaut.zad04.service;

import java.util.List;

import ug.jossowska.javaut.zad04.domain.ComicBook;
import ug.jossowska.javaut.zad04.domain.Creator;

public interface ComicBookManager {
	void addComicBook(ComicBook comicBook);
	ComicBook getById(long id);
	List<ComicBook> getAllComicBooks();
	List<ComicBook> getAllComicBooksByPublishingHouseName(String name);
	List<ComicBook> getAllComicBooksWithDateBetween(String date1, String date2);
	List<Creator> getAllCreators(long id);
	void deleteComicBook(ComicBook comicBook);
	void deleteComicBooks(List<ComicBook> comicBooks);
	void updateComicBook(ComicBook comicBook);
	void clearTable();
	List<ComicBook> getallComicBooksCreatorsMoreEqualThan(int number);
	List<ComicBook> getallComicBooksTitleMatchString(String match);
}
