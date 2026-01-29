package com.ty.HospitalManagementSystem.service;

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
		Address dbaddress=addressdao.updateAddress(id, address);
		if(dbaddress!=null) {
			return dbaddress;
		}else {
			return null;
		}
		
	}
	public Address deleteAddress(int id) {
		Address address=addressdao.deleteAddress(id);
		if(address!=null) {
			return address;
		}else {
			return null;
		}
		
	}
	public Address getaddressbyid(int id) {
		Address address=addressdao.getaddressbyid(id);
		if(address!=null) {
			return address;
		
	}else {
		return null;
	}
	}

	public ResponseStructure<List<Address>> getAllAddress() {
		List<Address> list=addressdao.getAllAddress();
		ResponseStructure<List<Address>>response=new ResponseStructure<>();
		response.setMessage("List of all products");
		response.setStatus(HttpStatus.OK.value());
		response.setData(list);

		return response;
	}
}
