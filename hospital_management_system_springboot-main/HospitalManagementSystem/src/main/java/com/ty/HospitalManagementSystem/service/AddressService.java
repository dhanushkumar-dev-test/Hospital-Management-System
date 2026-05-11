package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.dao.Addressdao;
import com.ty.HospitalManagementSystem.Entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

	@Autowired
	private Addressdao addressdao;

	public Address saveAddress(Address address) {
		return addressdao.saveAddress(address);
	}

	public Address updateAddress(int id, Address address) {
		return addressdao.updateAddress(id, address);
	}

	public Address deleteAddress(int id) {
		return addressdao.deleteAddress(id);
	}

	public Address getaddressbyid(int id) {
		return addressdao.getaddressbyid(id);
	}

	public List<Address> getAllAddress() {
		return addressdao.getAllAddress();
	}
}