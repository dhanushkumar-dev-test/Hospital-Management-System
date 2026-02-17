package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.dao.Addressdao;
import com.ty.HospitalManagementSystem.dto.Address;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.HospitalManagementSystem.dao.Persondao;
import com.ty.HospitalManagementSystem.dto.Person;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

	@Autowired
	private Persondao persondao;

	@Autowired
	private Addressdao addressdao;

	public Person savePerson(Person person) {
		if (person.getAddress() != null) {
			Address address = person.getAddress();
			if(address.getId()!= 0)
			{
				Address existingAddress=addressdao.getaddressbyid(address.getId())
						.orElseThrow(()->new IdNotFoundException("Address Not found" + address.getId()));
				person.setAddress(existingAddress);
			}
		}
		return persondao.savePerson(person);

	}

	public Person updatePerson(int id, Person person) {

		Person dbPerson = persondao.getpersonbyid(id)
				.orElseThrow(() ->
						new IdNotFoundException("Person not found with id " + id));

		dbPerson.setName(person.getName());
		dbPerson.setEmail(person.getEmail());
		dbPerson.setPhone(person.getPhone());

		if (person.getAddress() != null) {
			Address address = person.getAddress();

			if (address.getId() != 0) {
				Address existingAddress = addressdao.getaddressbyid(address.getId())
						.orElseThrow(() ->
								new IdNotFoundException("Address not found with id " + address.getId()));

				dbPerson.setAddress(existingAddress);
			} else {
				dbPerson.setAddress(address); // new address
			}
		}

		return persondao.savePerson(dbPerson);
	}


	public Person deletePerson(int id) {
		Person person = persondao.getpersonbyid(id)
				.orElseThrow(() ->
						new IdNotFoundException("Person not found with id " + id));

		return persondao.deletePerson(person);
	}


	public Person getpersonbyid(int id) {
		return persondao.getpersonbyid(id)
				.orElseThrow(() ->
						new IdNotFoundException("Person not found with id " + id));
	}

	public List<Person> getAllPerson() {
		return persondao.getAllPerson();
	}
}
