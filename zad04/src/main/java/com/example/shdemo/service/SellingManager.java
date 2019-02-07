package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.ComicBook;
import com.example.shdemo.domain.Person;

public interface SellingManager {
	
	void addClient(Person person);
	List<Person> getAllClients();
	void deleteClient(Person person);
	Person findClientByPin(String pin);
	
	Long addNewCar(ComicBook car);
	List<ComicBook> getAvailableCars();
	void disposeCar(Person person, ComicBook car);
	ComicBook findCarById(Long id);

	List<ComicBook> getOwnedCars(Person person);
	void sellCar(Long personId, Long carId);

}
