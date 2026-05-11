package com.ty.HospitalManagementSystem.dao;

import com.ty.HospitalManagementSystem.Entity.Hospital;
import com.ty.HospitalManagementSystem.repo.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class HospitalDao {

	@Autowired
	private HospitalRepo hospitalRepo;

	public Hospital savehospital(Hospital hospital) {
		return hospitalRepo.save(hospital);
	}

	public Hospital updatehospital(int id, Hospital hospital) {
		Hospital existingHospital = hospitalRepo.findById(id).orElse(null);

		if (existingHospital != null) {
			existingHospital.setName(hospital.getName());
			existingHospital.setEmail(hospital.getEmail());
			return hospitalRepo.save(existingHospital);
		}
		return null;
	}

	public Hospital deletehospital(int id) {
		Hospital hospital = hospitalRepo.findById(id).orElse(null);

		if (hospital != null) {
			hospitalRepo.delete(hospital);
			return hospital;
		}
		return null;
	}

	public Hospital gethospitalbyid(int id) {
		return hospitalRepo.findById(id).orElse(null);
	}

	public Hospital gethospitalbyemail(String email) {
		return hospitalRepo.findhospitalbyemail(email);
	}

	public Page<Hospital> getAllHospitals(Pageable pageable) {
		return hospitalRepo.findAll(pageable);
	}
}