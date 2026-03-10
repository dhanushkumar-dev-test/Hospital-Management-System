package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.dao.HospitalDao;
import com.ty.HospitalManagementSystem.dto.Hospital;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

	@Autowired
	private HospitalDao dao;

	public Hospital saveHospital(Hospital hospital) {
		return dao.savehospital(hospital);
	}

	public Hospital updateHospital(int id, Hospital hospital) {
		return dao.updatehospital(id, hospital)
				.orElseThrow(() ->
						new IdNotFoundException("Hospital not found for the id " + id));
	}

	public Hospital deleteHospital(int id) {
		return dao.deletehospital(id)
				.orElseThrow(() ->
						new IdNotFoundException("Hospital not found for the id " + id));
	}

	public Hospital getHospitalbyid(int id) {
		return dao.gethospitalbyid(id)
				.orElseThrow(() ->
						new IdNotFoundException("Hospital not found for the id " + id));
	}

	public Hospital gethospitalbyemail(String email) {
		return dao.gethospitalbyemail(email)
				.orElseThrow(() ->
						new IdNotFoundException("Hospital not found for the email " + email));
	}



	public List<Hospital> getAllHospital(int page, int size, String direction) {
		Sort sort = direction.equalsIgnoreCase("desc")?
				Sort.by("name").descending():
				Sort.by("name").ascending();

		Pageable pageable= PageRequest.of(page,size,sort);
		Page<Hospital> hospitalPage=dao.getAllHospitals(pageable);

		if (hospitalPage.isEmpty()) {
			throw new IdNotFoundException("No hospitals found");
		}

		return hospitalPage.getContent();


	}
}
