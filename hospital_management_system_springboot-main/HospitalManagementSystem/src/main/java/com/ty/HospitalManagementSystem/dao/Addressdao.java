package com.ty.HospitalManagementSystem.dao;

import com.ty.HospitalManagementSystem.Entity.Address;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;
import com.ty.HospitalManagementSystem.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Addressdao {

	@Autowired
	private AddressRepo addressRepo;

	// SAVE
	public Address saveAddress(Address address) {
		return addressRepo.save(address);
	}

	// UPDATE
	public Address updateAddress(int id, Address address) {

		Address existing = addressRepo.findById(id)
				.orElseThrow(() ->
						new IdNotFoundException("Address not found for id " + id));

		existing.setCity(address.getCity());
		existing.setState(address.getState());
		existing.setPincode(address.getPincode());

		return addressRepo.save(existing);
	}

	// DELETE
	public Address deleteAddress(int id) {

		Address address = addressRepo.findById(id)
				.orElseThrow(() ->
						new IdNotFoundException("Address not found for id " + id));

		addressRepo.delete(address);
		return address;
	}

	// GET BY ID
	public Address getaddressbyid(int id) {

		return addressRepo.findById(id)
				.orElseThrow(() ->
						new IdNotFoundException("Address not found for id " + id));
	}

	// GET ALL
	public List<Address> getAllAddress() {
		return addressRepo.findAll();
	}
}