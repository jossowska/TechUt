package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.ComicBook;
import com.example.shdemo.domain.Person;

@Component
@Transactional
public class SellingMangerHibernateImpl implements SellingManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addClient(Person person) {
		person.setId(null);
		sessionFactory.getCurrentSession().persist(person);
	}
	
	@Override
	public void deleteClient(Person person) {
		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
				person.getId());
		
		// lazy loading here
		for (ComicBook car : person.getCars()) {
			car.setSold(false);
			sessionFactory.getCurrentSession().update(car);
		}
		sessionFactory.getCurrentSession().delete(person);
	}

	@Override
	public List<ComicBook> getOwnedCars(Person person) {
		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
				person.getId());
		// lazy loading here - try this code without (shallow) copying
		List<ComicBook> cars = new ArrayList<ComicBook>(person.getCars());
		return cars;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Person> getAllClients() {
		return sessionFactory.getCurrentSession().getNamedQuery("person.all")
				.list();
	}

	@Override
	public Person findClientByPin(String pin) {
		return (Person) sessionFactory.getCurrentSession().getNamedQuery("person.byPin").setString("pin", pin).uniqueResult();
	}


	@Override
	public Long addNewCar(ComicBook car) {
		car.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(car);
	}

	@Override
	public void sellCar(Long personId, Long carId) {
		Person person = (Person) sessionFactory.getCurrentSession().get(
				Person.class, personId);
		ComicBook car = (ComicBook) sessionFactory.getCurrentSession()
				.get(ComicBook.class, carId);
		car.setSold(true);
		person.getCars().add(car);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ComicBook> getAvailableCars() {
		return sessionFactory.getCurrentSession().getNamedQuery("car.unsold")
				.list();
	}
	@Override
	public void disposeCar(Person person, ComicBook car) {

		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
				person.getId());
		car = (ComicBook) sessionFactory.getCurrentSession().get(ComicBook.class,
				car.getId());

		ComicBook toRemove = null;
		// lazy loading here (person.getCars)
		for (ComicBook aCar : person.getCars())
			if (aCar.getId().compareTo(car.getId()) == 0) {
				toRemove = aCar;
				break;
			}

		if (toRemove != null)
			person.getCars().remove(toRemove);

		car.setSold(false);
	}

	@Override
	public ComicBook findCarById(Long id) {
		return (ComicBook) sessionFactory.getCurrentSession().get(ComicBook.class, id);
	}

}
