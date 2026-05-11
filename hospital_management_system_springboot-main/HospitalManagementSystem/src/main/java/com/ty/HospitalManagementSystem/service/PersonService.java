package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.dao.Addressdao;
import com.ty.HospitalManagementSystem.dao.Persondao;
import com.ty.HospitalManagementSystem.Entity.Address;
import com.ty.HospitalManagementSystem.Entity.Person;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

	@Autowired
	private Persondao persondao;

	@Autowired
	private Addressdao addressdao;

	public Person savePerson(Person person) {

		if (person.getAddress() != null && person.getAddress().getId() != 0) {

			Address address = addressdao.getaddressbyid(person.getAddress().getId());

			if (address == null) {
				throw new IdNotFoundException("Address Not found " + person.getAddress().getId());
			}

			person.setAddress(address);
		}

		return persondao.savePerson(person);
	}

	public Person updatePerson(int id, Person person) {

		Person dbPerson = persondao.getPersonById(id);

		if (dbPerson == null) {
			throw new IdNotFoundException("Person not found with id " + id);
		}

		dbPerson.setName(person.getName());
		dbPerson.setEmail(person.getEmail());
		dbPerson.setPhone(person.getPhone());

		if (person.getAddress() != null) {

			if (person.getAddress().getId() != 0) {

				Address address = addressdao.getaddressbyid(person.getAddress().getId());

				if (address == null) {
					throw new IdNotFoundException("Address not found with id " + person.getAddress().getId());
				}

				dbPerson.setAddress(address);
			} else {
				dbPerson.setAddress(person.getAddress());
			}
		}

		return persondao.updatePerson(dbPerson);
	}

	public Person deletePerson(int id) {

		Person person = persondao.getPersonById(id);

		if (person == null) {
			throw new IdNotFoundException("Person not found with id " + id);
		}

		persondao.deletePerson(person);
		return person;
	}

	public Person getPersonById(int id) {

		Person person = persondao.getPersonById(id);

		if (person == null) {
			throw new IdNotFoundException("Person not found with id " + id);
		}

		return person;
	}

	public List<Person> getAllPersons(int page, int size, String direction) {

		Sort sort = direction.equalsIgnoreCase("desc")
				? Sort.by("name").descending()
				: Sort.by("name").ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Person> pageData = persondao.getAllPerson(pageable);

		if (pageData.isEmpty()) {
			throw new IdNotFoundException("No persons found");
		}

		return pageData.getContent();
	}
}