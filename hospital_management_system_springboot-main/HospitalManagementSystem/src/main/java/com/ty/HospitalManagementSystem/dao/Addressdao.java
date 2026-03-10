package com.ty.HospitalManagementSystem.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.HospitalManagementSystem.dto.Address;
import com.ty.HospitalManagementSystem.repo.AddressRepo;

import java.util.List;
import java.util.Optional;

@Repository
public class Addressdao {

	@Autowired
	private AddressRepo addressRepo;
	
	public Address saveAddress(Address address) {
		return addressRepo.save(address);
		
	}
	public Optional<Address> updateAddress(int id,Address address) {
		return addressRepo.findById(id).map(existingAddress->
		{
			existingAddress.setState(address.getState());
			existingAddress.setCity(address.getCity());
			existingAddress.setPincode(address.getPincode());
			return addressRepo.save(existingAddress);
		});
	}
	public Optional<Address> deleteAddress(int id) {
		return addressRepo.findById(id).map(address->{
			addressRepo.delete(address);
			return address;
		});
		
	}
	public Optional<Address> getaddressbyid(int id) {
		return addressRepo.findById(id);
		
	}


	public List<Address> getAllAddress() {
		return addressRepo.findAll();
	}
}
