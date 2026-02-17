package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.exception.IdNotFoundException;
import com.ty.HospitalManagementSystem.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.HospitalManagementSystem.dao.Addressdao;
import com.ty.HospitalManagementSystem.dto.Address;

import java.util.List;

@Service
public class AddressService {

	@Autowired
	private Addressdao addressdao;
	
	public Address saveAddress(Address address) {
		return addressdao.saveAddress(address);
		
	}
	public Address updateAddress(int id,Address address) {
		return addressdao.updateAddress(id,address)
				.orElseThrow(()->new IdNotFoundException("Address not found for the Id"+id));
	}
	public Address deleteAddress(int id) {
		return addressdao.deleteAddress(id)
				.orElseThrow(()->new IdNotFoundException("Address not found for the Id"+id));
		
	}
	public Address getaddressbyid(int id) {
		return addressdao.getaddressbyid(id)
				.orElseThrow(()->new IdNotFoundException("Address not found for the Id"+id));
	}

	public List<Address> getAllAddress() {
		return addressdao.getAllAddress();
	}
}
