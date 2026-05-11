package com.ty.HospitalManagementSystem.controller;

import com.ty.HospitalManagementSystem.Entity.Medorder;
import com.ty.HospitalManagementSystem.service.MedOrderService;
import com.ty.HospitalManagementSystem.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medorders")
@Tag(name = "MedOrder Management API", description = "Operations related to MedOrder entity")
public class MedOrderController {

	@Autowired
	private MedOrderService service;

	@PostMapping
	@Operation(summary = "Save MedOrder", description = "Creates a new MedOrder for an Encounter")
	@ApiResponse(responseCode = "201", description = "MedOrder created successfully")
	@ApiResponse(responseCode = "404", description = "Encounter not found")
	public ResponseEntity<ResponseStructure<Medorder>> saveMedorder(

			@Parameter(description = "Encounter ID", required = true)
			@RequestParam int eid,

			@Parameter(description = "MedOrder object to be created", required = true)
			@RequestBody Medorder medorder) {

		Medorder savedMedorder = service.saveMedorder(eid, medorder);

		ResponseStructure<Medorder> response = new ResponseStructure<>();
		response.setStatus(201);
		response.setMessage("MedOrder saved successfully");
		response.setData(savedMedorder);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update MedOrder", description = "Updates MedOrder details using ID")
	@ApiResponse(responseCode = "200", description = "MedOrder updated successfully")
	@ApiResponse(responseCode = "404", description = "MedOrder not found")
	public ResponseEntity<ResponseStructure<Medorder>> updateMedorder(

			@Parameter(description = "MedOrder ID", required = true)
			@PathVariable int id,

			@Parameter(description = "Updated MedOrder object", required = true)
			@RequestBody Medorder medorder) {

		Medorder updatedMedorder = service.updateMedorder(id, medorder);

		ResponseStructure<Medorder> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("MedOrder updated successfully");
		response.setData(updatedMedorder);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete MedOrder", description = "Deletes a MedOrder using ID")
	@ApiResponse(responseCode = "200", description = "MedOrder deleted successfully")
	@ApiResponse(responseCode = "404", description = "MedOrder not found")
	public ResponseEntity<ResponseStructure<Medorder>> deleteMedorder(

			@Parameter(description = "MedOrder ID", required = true)
			@PathVariable int id) {

		Medorder deletedMedorder = service.deleteMedorder(id);

		ResponseStructure<Medorder> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("MedOrder deleted successfully");
		response.setData(deletedMedorder);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get MedOrder By ID", description = "Fetch MedOrder using ID")
	@ApiResponse(responseCode = "200", description = "MedOrder fetched successfully")
	@ApiResponse(responseCode = "404", description = "MedOrder not found")
	public ResponseEntity<ResponseStructure<Medorder>> getMedorderbyid(

			@Parameter(description = "MedOrder ID", required = true)
			@PathVariable int id) {

		Medorder medorder = service.getMedorderbyid(id);

		ResponseStructure<Medorder> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("MedOrder fetched successfully");
		response.setData(medorder);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}