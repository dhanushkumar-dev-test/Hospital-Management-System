package com.ty.HospitalManagementSystem.dao;

import com.ty.HospitalManagementSystem.dto.Person;
import com.ty.HospitalManagementSystem.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class Persondao {

	@Autowired
	private PersonRepo repo;

	public Person savePerson(Person person) {
		return repo.save(person);

	}

	public Person updatePerson(int id, Person person) {

		return repo.save(person);

	}

	public Person deletePerson(Person person) {
		repo.delete(person);
		return person;
	}

	public Optional<Person> getpersonbyid(int id) {
		return repo.findById(id);

	}


	public Page<Person> getAllPerson(Pageable pageable) {
		return repo.findAll(pageable);
	}
}
