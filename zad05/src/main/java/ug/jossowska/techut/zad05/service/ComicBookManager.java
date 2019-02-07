package ug.jossowska.techut.zad05.service;

import java.util.List;



import ug.jossowska.techut.zad05.domain.ComicBook;
import ug.jossowska.techut.zad05.domain.Creator;
import ug.jossowska.techut.zad05.domain.Details;
import ug.jossowska.techut.zad05.domain.PublishingHouse;

public interface ComicBookManager {
	void addComicBook(ComicBook comicBook);
	List<ComicBook> getAllComics();
	void deleteComicBook(ComicBook comicBook);
	ComicBook findComicBookByName(String name);
	ComicBook findComicBookById(Long id);
	
	void addCreator(Creator creator);
    void deleteCreator(Creator creator);
    Creator findCreatorById(Long id);
	List<Creator> getAllCreators();
	List <ComicBook> getCreatorComics(Creator creator);

    void addPublishingHouse(PublishingHouse publishingHouse);
    void deletePublishingHouse(PublishingHouse publishingHouse);
    PublishingHouse findPublishingHouseById(Long id);
    List<PublishingHouse> getAllPublishingHouses();
    List<PublishingHouse> findComicBookPublishingHouses(ComicBook comicBook);

    void addDetails(Details details);
    void deleteDetails(Details details);
    Details findDetailsById(Long id);
    List<Details> getAllDetails();
	

	void addComicBookToCreator(ComicBook b, Creator o);
	void removeComicBookFromCreator(ComicBook b, Creator o);
	void addPublishingHouseToComicBook(PublishingHouse t1, ComicBook b);
}

