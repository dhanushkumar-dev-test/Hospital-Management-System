package com.ty.HospitalManagementSystem.controller;

import javax.validation.Valid;

import com.ty.HospitalManagementSystem.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ty.HospitalManagementSystem.dto.Address;
import com.ty.HospitalManagementSystem.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	@Autowired
	private AddressService service;
	
	@PostMapping("/saveaddress")
	public Address saveAddress(@Valid @RequestBody Address address) {
		return service.saveAddress(address);
	}
	@PutMapping("/updateaddress")
	public Address updateaAddress(@Valid @RequestParam int id,@RequestBody Address address) {
		return service.updateAddress(id, address);
	}
	@DeleteMapping("/deleteaddress")
	public Address deleteAddress(@Valid @RequestParam int id) {
		return service.deleteAddress(id);
		
	}
	@GetMapping("/getaddressbyid")
	public Address getaddressbyid(@Valid @RequestParam int id) {
		return service.getaddressbyid(id);
	}
	@GetMapping("/getAllAdresses")
	public ResponseEntity<ResponseStructure<List<Address>>>getAllAddress()
		{
			return new ResponseEntity<>(service.getAllAddress(), HttpStatus.OK);
		}
		

}
