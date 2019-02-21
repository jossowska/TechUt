package ug.jossowska.javaut.zad04.service;

import java.util.List;

import ug.jossowska.javaut.zad04.domain.Topic;

public interface TopicManager {
	void addTopic(Topic topic);
	Topic getById(long id);
	List<Topic> getAll();
	void deleteTopic(Topic topic);
	void updateTopic(Topic Topic);
	void clearTable();
}
