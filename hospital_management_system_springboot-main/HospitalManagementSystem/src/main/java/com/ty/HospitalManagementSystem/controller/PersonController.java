package com.ty.HospitalManagementSystem.controller;

import com.ty.HospitalManagementSystem.dto.Person;
import com.ty.HospitalManagementSystem.service.PersonService;
import com.ty.HospitalManagementSystem.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Person Management API", description = "Operations related to Person entity")
public class PersonController {

	@Autowired
	private PersonService service;

	@PostMapping
	@Operation(summary = "Save Person", description = "Creates a new person with optional address mapping")
	@ApiResponse(responseCode = "201", description = "Person created successfully")
	@ApiResponse(responseCode = "400", description = "Invalid request body")
	public ResponseEntity<ResponseStructure<Person>> savePerson(
			@Parameter(description = "Person object to be created", required = true)
			@RequestBody Person person) {

		Person savedPerson = service.savePerson(person);

		ResponseStructure<Person> response = new ResponseStructure<>();
		response.setStatus(201);
		response.setMessage("Person saved successfully");
		response.setData(savedPerson);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update Person", description = "Updates person details using ID")
	@ApiResponse(responseCode = "200", description = "Person updated successfully")
	@ApiResponse(responseCode = "404", description = "Person not found")
	public ResponseEntity<ResponseStructure<Person>> updatePerson(
			@Parameter(description = "Person ID", required = true)
			@PathVariable int id,

			@Parameter(description = "Updated person object", required = true)
			@RequestBody Person person) {

		Person updatedPerson = service.updatePerson(id, person);

		ResponseStructure<Person> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("Person updated successfully");
		response.setData(updatedPerson);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Person", description = "Deletes a person by ID")
	@ApiResponse(responseCode = "200", description = "Person deleted successfully")
	@ApiResponse(responseCode = "404", description = "Person not found")
	public ResponseEntity<ResponseStructure<Person>> deletePerson(
			@Parameter(description = "Person ID", required = true)
			@PathVariable int id) {

		Person deletedPerson = service.deletePerson(id);

		ResponseStructure<Person> response = new ResponseStructure<>();
		response.setStatus(204);
		response.setMessage("Person deleted successfully");
		response.setData(deletedPerson);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get Person By ID", description = "Fetch a person using ID")
	@ApiResponse(responseCode = "200", description = "Person fetched successfully")
	@ApiResponse(responseCode = "404", description = "Person not found")
	public ResponseEntity<ResponseStructure<Person>> getPersonById(
			@Parameter(description = "Person ID", required = true)
			@PathVariable int id) {

		Person person = service.getpersonbyid(id);

		ResponseStructure<Person> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("Person fetched successfully");
		response.setData(person);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	@Operation(
			summary = "Get all persons with pagination",
			description = "Fetch persons using pagination. Sorted by name (ASC)"
	)
	@ApiResponse(responseCode = "200", description = "Persons fetched successfully")
	@ApiResponse(responseCode = "404", description = "No persons found")
	public ResponseEntity<ResponseStructure<List<Person>>> getAllPerson(

			@Parameter(description = "Page number (starts from 0)", example = "0")
			@RequestParam(defaultValue = "0") int page,

			@Parameter(description = "Number of records per page", example = "5")
			@RequestParam(defaultValue = "5") int size ,

			@Parameter(description = "Sort direction: asc or desc", example = "asc")
			@RequestParam(defaultValue = "asc") String direction
	) {

		List<Person> persons = service.getAllPersons(page, size,direction);

		ResponseStructure<List<Person>> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Persons fetched successfully");
		response.setData(persons);

		return ResponseEntity.ok(response);
	}
}
