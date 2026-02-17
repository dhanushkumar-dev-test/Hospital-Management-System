package com.ty.HospitalManagementSystem.controller;

import com.ty.HospitalManagementSystem.dto.Address;
import com.ty.HospitalManagementSystem.service.AddressService;
import com.ty.HospitalManagementSystem.util.ResponseStructure;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@Api(tags = "Address Management API", description = "Operations related to Address entity")
public class AddressController {

	@Autowired
	private AddressService service;

	@PostMapping
	@ApiOperation(value = "Save Address", notes = "Creates a new address record")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Address created successfully"),
			@ApiResponse(code = 400, message = "Invalid request body")
	})
	public ResponseEntity<ResponseStructure<Address>> saveAddress(
			@ApiParam(value = "Address object to be created", required = true)
			@Valid @RequestBody Address address) {

		Address savedAddress = service.saveAddress(address);

		ResponseStructure<Address> response = new ResponseStructure<>();
		response.setMessage("Address saved successfully");
		response.setStatus(HttpStatus.CREATED.value());
		response.setData(savedAddress);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Update Address", notes = "Updates address by ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Address updated successfully"),
			@ApiResponse(code = 404, message = "Address not found")
	})
	public ResponseEntity<ResponseStructure<Address>> updateAddress(
			@ApiParam(value = "Address ID", required = true)
			@PathVariable int id,

			@ApiParam(value = "Updated address object", required = true)
			@Valid @RequestBody Address address) {

		Address updatedAddress = service.updateAddress(id, address);

		ResponseStructure<Address> response = new ResponseStructure<>();
		response.setMessage("Address updated successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setData(updatedAddress);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete Address", notes = "Deletes address by ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Address deleted successfully"),
			@ApiResponse(code = 404, message = "Address not found")
	})
	public ResponseEntity<ResponseStructure<Address>> deleteAddress(
			@ApiParam(value = "Address ID", required = true)
			@PathVariable int id) {

		Address deletedAddress = service.deleteAddress(id);

		ResponseStructure<Address> response = new ResponseStructure<>();
		response.setMessage("Address deleted successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setData(deletedAddress);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get Address By ID", notes = "Fetch address using ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Address fetched successfully"),
			@ApiResponse(code = 404, message = "Address not found")
	})
	public ResponseEntity<ResponseStructure<Address>> getAddressById(
			@ApiParam(value = "Address ID", required = true)
			@PathVariable int id) {

		Address address = service.getaddressbyid(id);

		ResponseStructure<Address> response = new ResponseStructure<>();
		response.setMessage("Address fetched successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setData(address);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "Get All Addresses", notes = "Fetch all address records")
	@ApiResponses({
			@ApiResponse(code = 200, message = "All addresses fetched successfully")
	})
	public ResponseEntity<ResponseStructure<List<Address>>> getAllAddress() {

		List<Address> addresses = service.getAllAddress();

		ResponseStructure<List<Address>> response = new ResponseStructure<>();
		response.setMessage("All addresses fetched successfully");
		response.setStatus(HttpStatus.OK.value());
		response.setData(addresses);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
