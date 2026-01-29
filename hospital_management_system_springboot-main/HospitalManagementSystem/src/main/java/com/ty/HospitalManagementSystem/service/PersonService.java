package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.dao.Addressdao;
import com.ty.HospitalManagementSystem.dto.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.HospitalManagementSystem.dao.Persondao;
import com.ty.HospitalManagementSystem.dto.Person;

@Service
public class PersonService {

	@Autowired
	private Persondao persondao;

	@Autowired
	private Addressdao addressdao;

	public Person savePerson(Person person) {
//		if (person.getAddress() != null) {
//			Address address = person.getAddress();
//			if(address.get)
		return persondao.savePerson(person);

	}

	public Person updatePerson(int id, Person person) {
		Person dbperson = persondao.updatePerson(id, person);
		if (dbperson != null) {
			return dbperson;
		} else {
			return null;
		}

	}

	public Person deletePerson(int id) {
		Person person = persondao.deletePerson(id);
		if (person != null) {
			return person;
		} else {
			return null;
		}

	}

	public Person getpersonbyid(int id) {
		Person person = persondao.getpersonbyid(id);
		if (person != null) {
			return person;
		} else {
			return null;
		}

	}
}
