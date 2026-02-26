package com.ty.HospitalManagementSystem.controller;

import com.ty.HospitalManagementSystem.dto.Hospital;
import com.ty.HospitalManagementSystem.service.HospitalService;
import com.ty.HospitalManagementSystem.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hospital")
@Tag(name = "Hospital Management API", description = "Operations related to Hospital entity")
public class HospitalController {

	@Autowired
	private HospitalService service;

	@PostMapping("/save")
	@Operation(summary = "Save hospital", description = "API is used to save hospital")
	@ApiResponse(responseCode = "201", description = "Successfully saved")
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(
			@Parameter(description = "Hospital object to be saved", required = true)
			@Valid @RequestBody Hospital hospital) {

		Hospital savedHospital = service.saveHospital(hospital);

		ResponseStructure<Hospital> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Successfully saved");
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setData(savedHospital);

		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	@Operation(summary = "Update hospital", description = "API is used to update hospital")
	@ApiResponse(responseCode = "200", description = "Successfully updated")
	@ApiResponse(responseCode = "404", description = "ID not found")
	public ResponseEntity<ResponseStructure<Hospital>> updateHospital(
			@Parameter(description = "Hospital ID", required = true)
			@RequestParam int id,

			@Parameter(description = "Updated hospital object", required = true)
			@Valid @RequestBody Hospital hospital) {

		Hospital updatedHospital = service.updateHospital(id, hospital);

		ResponseStructure<Hospital> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Successfully updated");
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setData(updatedHospital);

		return ResponseEntity.ok(responseStructure);
	}

	@DeleteMapping("/delete")
	@Operation(summary = "Delete hospital", description = "API is used to delete hospital")
	@ApiResponse(responseCode = "200", description = "Successfully deleted")
	@ApiResponse(responseCode = "404", description = "ID not found")
	public ResponseEntity<ResponseStructure<Hospital>> deleteHospital(
			@Parameter(description = "Hospital ID", required = true)
			@RequestParam int id) {

		Hospital deletedHospital = service.deleteHospital(id);

		ResponseStructure<Hospital> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Deleted successfully");
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setData(deletedHospital);

		return ResponseEntity.ok(responseStructure);
	}

	@GetMapping("/getById")
	@Operation(summary = "Get hospital by ID", description = "API is used to fetch hospital by ID")
	@ApiResponse(responseCode = "200", description = "Successfully found")
	@ApiResponse(responseCode = "404", description = "ID not found")
	public ResponseEntity<ResponseStructure<Hospital>> getHospitalById(
			@Parameter(description = "Hospital ID", required = true)
			@RequestParam int id) {

		Hospital hospital = service.getHospitalbyid(id);

		ResponseStructure<Hospital> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Successfully found");
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setData(hospital);

		return ResponseEntity.ok(responseStructure);
	}

	@GetMapping("/getByEmail")
	@Operation(summary = "Get hospital by email", description = "API is used to fetch hospital by email")
	@ApiResponse(responseCode = "200", description = "Successfully found")
	@ApiResponse(responseCode = "404", description = "Email not found")
	public ResponseEntity<ResponseStructure<Hospital>> getHospitalByEmail(
			@Parameter(description = "Hospital email", required = true)
			@RequestParam String email) {

		Hospital hospital = service.gethospitalbyemail(email);

		ResponseStructure<Hospital> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Successfully found");
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setData(hospital);

		return ResponseEntity.ok(responseStructure);
	}

	@GetMapping("/getAllHospital")
	@Operation(
			summary = "Get all hospitals with pagination",
			description = "Fetch hospitals using pagination and sorting. Default sort = name ASC"
	)
	@ApiResponse(responseCode = "200", description = "Hospitals fetched successfully")
	@ApiResponse(responseCode = "404", description = "No hospitals found")
	public ResponseEntity<ResponseStructure<List<Hospital>>> getAllHospital(

			@Parameter(description = "Page number (starts from 0)", example = "0")
			@RequestParam(defaultValue = "0") int page,

			@Parameter(description = "Number of records per page", example = "5")
			@RequestParam(defaultValue = "5") int size,

			@Parameter(description = "Sort direction: asc or desc", example = "asc")
			@RequestParam(defaultValue = "asc") String direction
	) {

		List<Hospital> hospitals = service.getAllHospital(page, size, direction);

		ResponseStructure<List<Hospital>> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Hospitals fetched successfully");
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setData(hospitals);

		return ResponseEntity.ok(responseStructure);
	}
}
