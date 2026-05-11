package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.dao.HospitalDao;
import com.ty.HospitalManagementSystem.Entity.Hospital;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
		Hospital updated = dao.updatehospital(id, hospital);

		if (updated == null) {
			throw new IdNotFoundException("Hospital not found for id " + id);
		}
		return updated;
	}

	public Hospital deleteHospital(int id) {
		Hospital deleted = dao.deletehospital(id);

		if (deleted == null) {
			throw new IdNotFoundException("Hospital not found for id " + id);
		}
		return deleted;
	}

	public Hospital getHospitalbyid(int id) {
		Hospital hospital = dao.gethospitalbyid(id);

		if (hospital == null) {
			throw new IdNotFoundException("Hospital not found for id " + id);
		}
		return hospital;
	}

	public Hospital gethospitalbyemail(String email) {
		Hospital hospital = dao.gethospitalbyemail(email);

		if (hospital == null) {
			throw new IdNotFoundException("Hospital not found for email " + email);
		}
		return hospital;
	}

	public List<Hospital> getAllHospital(int page, int size, String direction) {

		Sort sort = direction.equalsIgnoreCase("desc")
				? Sort.by("name").descending()
				: Sort.by("name").ascending();

		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Hospital> hospitalPage = dao.getAllHospitals(pageable);

		if (hospitalPage.isEmpty()) {
			throw new IdNotFoundException("No hospitals found");
		}

		return hospitalPage.getContent();
	}
}