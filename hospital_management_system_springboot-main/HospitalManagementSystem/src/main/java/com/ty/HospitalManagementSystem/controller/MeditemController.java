package com.ty.HospitalManagementSystem.controller;

import com.ty.HospitalManagementSystem.Entity.MedItems;
import com.ty.HospitalManagementSystem.service.MedItemService;
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
@RequestMapping("/api/meditems")
@Tag(name = "MedItems Management API", description = "Operations related to MedItems entity")
public class MeditemController {

	@Autowired
	private MedItemService service;

	@PostMapping
	@Operation(summary = "Save MedItems", description = "Creates new MedItems for a MedOrder")
	@ApiResponse(responseCode = "201", description = "MedItems created successfully")
	@ApiResponse(responseCode = "404", description = "MedOrder not found")
	public ResponseEntity<ResponseStructure<MedItems>> saveMedItems(

			@Parameter(description = "MedOrder ID", required = true)
			@RequestParam int mid,

			@Parameter(description = "MedItems object to be created", required = true)
			@RequestBody MedItems meditems) {

		MedItems savedMedItems = service.saveMedItems(mid, meditems);

		ResponseStructure<MedItems> response = new ResponseStructure<>();
		response.setStatus(201);
		response.setMessage("MedItems saved successfully");
		response.setData(savedMedItems);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update MedItems", description = "Updates MedItems details using ID")
	@ApiResponse(responseCode = "200", description = "MedItems updated successfully")
	@ApiResponse(responseCode = "404", description = "MedItems not found")
	public ResponseEntity<ResponseStructure<MedItems>> updateMedItems(

			@Parameter(description = "MedItems ID", required = true)
			@PathVariable int id,

			@Parameter(description = "Updated MedItems object", required = true)
			@RequestBody MedItems meditems) {

		MedItems updatedMedItems = service.updateMedItems(id, meditems);

		ResponseStructure<MedItems> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("MedItems updated successfully");
		response.setData(updatedMedItems);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete MedItems", description = "Deletes MedItems using ID")
	@ApiResponse(responseCode = "200", description = "MedItems deleted successfully")
	@ApiResponse(responseCode = "404", description = "MedItems not found")
	public ResponseEntity<ResponseStructure<MedItems>> deleteMedItems(

			@Parameter(description = "MedItems ID", required = true)
			@PathVariable int id) {

		MedItems deletedMedItems = service.deleteMedItems(id);

		ResponseStructure<MedItems> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("MedItems deleted successfully");
		response.setData(deletedMedItems);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get MedItems By ID", description = "Fetch MedItems using ID")
	@ApiResponse(responseCode = "200", description = "MedItems fetched successfully")
	@ApiResponse(responseCode = "404", description = "MedItems not found")
	public ResponseEntity<ResponseStructure<MedItems>> getmeditemsbyid(

			@Parameter(description = "MedItems ID", required = true)
			@PathVariable int id) {

		MedItems medItems = service.getmeditemsbyid(id);

		ResponseStructure<MedItems> response = new ResponseStructure<>();
		response.setStatus(200);
		response.setMessage("MedItems fetched successfully");
		response.setData(medItems);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}