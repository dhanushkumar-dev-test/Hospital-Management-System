package com.ty.HospitalManagementSystem.controller;

import com.ty.HospitalManagementSystem.dto.Person;
import com.ty.HospitalManagementSystem.service.PersonService;
import com.ty.HospitalManagementSystem.util.ResponseStructure;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@Api(tags = "Person Management API", description = "Operations related to Person entity")
public class PersonController {

	@Autowired
	private PersonService service;

	@PostMapping
	@ApiOperation(value = "Save Person", notes = "Creates a new person with optional address mapping")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Person created successfully"),
			@ApiResponse(code = 400, message = "Invalid request body")
	})
	public ResponseEntity<ResponseStructure<Person>> savePerson(
			@ApiParam(value = "Person object to be created", required = true)
			@RequestBody Person person) {

		Person savedPerson = service.savePerson(person);

		ResponseStructure<Person> response = new ResponseStructure<>();
		response.setStatus(201);
		response.setMessage("Person saved successfully");
		response.setData(savedPerson);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Update Person", notes = "Updates person details using ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Person updated successfully"),
			@ApiResponse(code = 404, message = "Person not found")
	})
	public ResponseEntity<ResponseStructure<Person>> updatePerson(
			@ApiParam(value = "Person ID", required = true)
			@PathVariable int id,

			@ApiParam(value = "Updated person object", required = true)
			@RequestBody Person person) {

		Person updatedPerson = service.updatePerson(id, person);

		ResponseStructure<Person> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("Person updated successfully");
		response.setData(updatedPerson);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete Person", notes = "Deletes a person by ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Person deleted successfully"),
			@ApiResponse(code = 404, message = "Person not found")
	})
	public ResponseEntity<ResponseStructure<Person>> deletePerson(
			@ApiParam(value = "Person ID", required = true)
			@PathVariable int id) {

		Person deletedPerson = service.deletePerson(id);

		ResponseStructure<Person> response = new ResponseStructure<>();
		response.setStatus(204);
		response.setMessage("Person deleted successfully");
		response.setData(deletedPerson);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get Person By ID", notes = "Fetch a person using ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Person fetched successfully"),
			@ApiResponse(code = 404, message = "Person not found")
	})
	public ResponseEntity<ResponseStructure<Person>> getPersonById(
			@ApiParam(value = "Person ID", required = true)
			@PathVariable int id) {

		Person person = service.getpersonbyid(id);

		ResponseStructure<Person> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("Person fetched successfully");
		response.setData(person);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "Get All Persons", notes = "Fetch all persons from database")
	@ApiResponses({
			@ApiResponse(code = 200, message = "All persons fetched successfully")
	})
	public ResponseEntity<ResponseStructure<List<Person>>> getAllPerson() {

		List<Person> person = service.getAllPerson();

		ResponseStructure<List<Person>> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("All Person fetched successfully");
		response.setData(person);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
