package com.ty.HospitalManagementSystem.dao;

import com.ty.HospitalManagementSystem.Entity.Person;
import com.ty.HospitalManagementSystem.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class Persondao {

	@Autowired
	private PersonRepo repo;

	public Person savePerson(Person person) {
		return repo.save(person);
	}

	public Person updatePerson(Person person) {
		return repo.save(person);
	}

	public void deletePerson(Person person) {
		repo.delete(person);
	}

	public Person getPersonById(int id) {
		return repo.findById(id).orElse(null);
	}

	public Page<Person> getAllPerson(Pageable pageable) {
		return repo.findAll(pageable);
	}
}