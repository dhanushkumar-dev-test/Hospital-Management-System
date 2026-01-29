package com.ty.HospitalManagementSystem.controller;

import com.ty.HospitalManagementSystem.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ty.HospitalManagementSystem.dto.Person;
import com.ty.HospitalManagementSystem.service.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

	@Autowired
	private PersonService service;

	@PostMapping
	public ResponseStructure<Person> savePerson(@RequestBody Person person) {
		Person savedPerson = service.savePerson(person);
		ResponseStructure<Person> response = new ResponseStructure<>();
		response.setStatus(201);
		response.setMessage("Person saved successfully");
		response.setData(savedPerson);
		return response;
	}

	@PutMapping("/{id}")
	public ResponseStructure<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
		Person updatedPerson = service.updatePerson(id, person);
		ResponseStructure<Person> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("Person updated successfully");
		response.setData(updatedPerson);
		return response;
	}

	@DeleteMapping("/{id}")
	public ResponseStructure<Person> deletePerson(@PathVariable int id) {
		Person deletedPerson = service.deletePerson(id);
		ResponseStructure<Person> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("Person deleted successfully");
		response.setData(deletedPerson);
		return response;
	}

	@GetMapping("/{id}")
	public ResponseStructure<Person> getPersonById(@PathVariable int id) {
		Person person = service.getpersonbyid(id);
		ResponseStructure<Person> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("Person fetched successfully");
		response.setData(person);
		return response;
	}
}