package com.ty.HospitalManagementSystem.controller;

import com.ty.HospitalManagementSystem.dto.Address;
import com.ty.HospitalManagementSystem.service.AddressService;
import com.ty.HospitalManagementSystem.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@Tag(name = "Address Management API", description = "Operations related to Address entity")
public class AddressController {

	@Autowired
	private AddressService service;

	@PostMapping
	@Operation(summary = "Save Address", description = "Creates a new address record")
	@ApiResponse(responseCode = "201", description = "Address created successfully")
	@ApiResponse(responseCode = "400", description = "Invalid request body")
	public ResponseEntity<ResponseStructure<Address>> saveAddress(
			@Parameter(description = "Address object to be created", required = true)
			@Valid @RequestBody Address address) {

		Address savedAddress = service.saveAddress(address);

		ResponseStructure<Address> response = new ResponseStructure<>();
		response.setMessage("Address saved successfully");
		response.setStatus(HttpStatus.CREATED.value());
		response.setData(savedAddress);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update Address", description = "Updates address by ID")
	@ApiResponse(responseCode = "200", description = "Address updated successfully")
	@ApiResponse(responseCode = "404", description = "Address not found")
	public ResponseEntity<ResponseStructure<Address>> updateAddress(
			@Parameter(description = "Address ID", required = true)
			@PathVariable int id,

			@Parameter(description = "Updated address object", required = true)
			@Valid @RequestBody Address address) {

		Address updatedAddress = service.updateAddress(id, address);

		ResponseStructure<Address> response = new ResponseStructure<>();
		response.setMessage("Address updated successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setData(updatedAddress);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Address", description = "Deletes address by ID")
	@ApiResponse(responseCode = "200", description = "Address deleted successfully")
	@ApiResponse(responseCode = "404", description = "Address not found")
	public ResponseEntity<ResponseStructure<Address>> deleteAddress(
			@Parameter(description = "Address ID", required = true)
			@PathVariable int id) {

		Address deletedAddress = service.deleteAddress(id);

		ResponseStructure<Address> response = new ResponseStructure<>();
		response.setMessage("Address deleted successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setData(deletedAddress);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get Address By ID", description = "Fetch address using ID")
	@ApiResponse(responseCode = "200", description = "Address fetched successfully")
	@ApiResponse(responseCode = "404", description = "Address not found")
	public ResponseEntity<ResponseStructure<Address>> getAddressById(
			@Parameter(description = "Address ID", required = true)
			@PathVariable int id) {

		Address address = service.getaddressbyid(id);

		ResponseStructure<Address> response = new ResponseStructure<>();
		response.setMessage("Address fetched successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setData(address);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getAllPersons")
	@Operation(summary = "Get All Addresses", description = "Fetch all address records")
	@ApiResponse(responseCode = "200", description = "All addresses fetched successfully")
	public ResponseEntity<ResponseStructure<List<Address>>> getAllAddress() {

		List<Address> addresses = service.getAllAddress();

		ResponseStructure<List<Address>> response = new ResponseStructure<>();
		response.setMessage("All addresses fetched successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setData(addresses);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
