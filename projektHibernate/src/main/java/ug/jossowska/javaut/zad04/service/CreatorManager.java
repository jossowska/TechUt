package ug.jossowska.javaut.zad04.service;

import java.util.List;

import ug.jossowska.javaut.zad04.domain.Creator;

public interface CreatorManager {
	void addCreator(Creator creator);
	Creator getById(long id);
	List<Creator> getAll();
	void deleteCreator(Creator creator);
	void updateCreator(Creator creator);
	void clearTable();
}
