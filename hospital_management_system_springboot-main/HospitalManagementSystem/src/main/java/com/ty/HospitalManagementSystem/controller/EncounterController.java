package com.ty.HospitalManagementSystem.controller;

import com.ty.HospitalManagementSystem.Entity.Encounter;
import com.ty.HospitalManagementSystem.service.EncounterService;
import com.ty.HospitalManagementSystem.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/encounters")
@Validated
@Tag(name = "Encounter Management API", description = "Operations related to Encounter entity")
public class EncounterController {

	@Autowired
	private EncounterService service;

	@PostMapping
	@Operation(summary = "Save Encounter", description = "Creates a new encounter")
	@ApiResponse(responseCode = "201", description = "Encounter saved successfully")
	@ApiResponse(responseCode = "400", description = "Invalid request")
	public ResponseEntity<ResponseStructure<Encounter>> saveEncounter(

			@Parameter(description = "Encounter object to be created", required = true)
			@Valid @RequestBody Encounter encounter,

			@Parameter(description = "Person ID", required = true)
			@RequestParam int pid,

			@Parameter(description = "Branch ID", required = true)
			@RequestParam int bid) {

		Encounter savedEncounter = service.saveEncounter(encounter, pid, bid);

		ResponseStructure<Encounter> response = new ResponseStructure<>();
		response.setMessage("Encounter saved successfully");
		response.setStatus(HttpStatus.CREATED.value());
		response.setData(savedEncounter);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update Encounter", description = "Updates encounter by ID")
	@ApiResponse(responseCode = "200", description = "Encounter updated successfully")
	@ApiResponse(responseCode = "404", description = "Encounter not found")
	public ResponseEntity<ResponseStructure<Encounter>> updateEncounter(

			@Parameter(description = "Encounter ID", required = true)
			@PathVariable int id,

			@Parameter(description = "Updated Encounter object", required = true)
			@RequestBody Encounter encounter,

			@Parameter(description = "Branch ID", required = true)
			@RequestParam int bid) {

		Encounter updatedEncounter = service.updateEncounter(id, encounter, bid);

		ResponseStructure<Encounter> response = new ResponseStructure<>();
		response.setMessage("Encounter updated successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setData(updatedEncounter);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Encounter", description = "Deletes encounter by ID")
	@ApiResponse(responseCode = "200", description = "Encounter deleted successfully")
	@ApiResponse(responseCode = "404", description = "Encounter not found")
	public ResponseEntity<ResponseStructure<Encounter>> deleteEncounter(

			@Parameter(description = "Encounter ID", required = true)
			@PathVariable int id) {

		Encounter deletedEncounter = service.deleteEncounter(id);

		ResponseStructure<Encounter> response = new ResponseStructure<>();
		response.setMessage("Encounter deleted successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setData(deletedEncounter);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get Encounter By ID", description = "Fetch encounter using ID")
	@ApiResponse(responseCode = "200", description = "Encounter fetched successfully")
	@ApiResponse(responseCode = "404", description = "Encounter not found")
	public ResponseEntity<ResponseStructure<Encounter>> getEncounterById(

			@Parameter(description = "Encounter ID", required = true)
			@PathVariable int id) {

		Encounter encounter = service.getEncounterById(id);

		ResponseStructure<Encounter> response = new ResponseStructure<>();
		response.setMessage("Encounter fetched successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setData(encounter);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}