package ug.jossowska.techut.zad03.service;

import java.util.List;


import ug.jossowska.techut.zad03.domain.ComicBook;

public interface ComicBookService {
	void addComicBook(ComicBook comicbook);
	List<ComicBook> getAllComics();
	List<ComicBook> getAllPopularOrUnpopularComics(boolean isPopular);
	ComicBook getComicBookById(int id);
	ComicBook getComicBookByTitle(String title);
	void deleteAllComics();
	boolean deleteComicBookById(int id);
	boolean addAllComics(List<ComicBook> comics);
}
