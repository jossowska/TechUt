package ug.jossowska.javaut.zad04.service;

import java.util.List;

import ug.jossowska.javaut.zad04.domain.Detail;

public interface DetailManager {
	void addDetail(Detail detail);
	Detail getById(long id);
	List<Detail> getAll();
	void deleteDetail(Detail detail);
	void updateDetail(Detail detail);
	void clearTable();
}
