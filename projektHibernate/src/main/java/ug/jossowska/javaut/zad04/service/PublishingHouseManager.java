package ug.jossowska.javaut.zad04.service;

import java.util.List;

import ug.jossowska.javaut.zad04.domain.PublishingHouse;

public interface PublishingHouseManager {
	void addPublishingHouse(PublishingHouse publishingHouse);
	List<PublishingHouse> getAllPublishingHouses();
	PublishingHouse getPublishingHouseById(long id);
	void deletePublishingHouse(PublishingHouse publishingHouse);
	void updatePublishingHouse(PublishingHouse publishingHouse);
	PublishingHouse getPublishingHouseByName(String name);
	void clearTable();
}
