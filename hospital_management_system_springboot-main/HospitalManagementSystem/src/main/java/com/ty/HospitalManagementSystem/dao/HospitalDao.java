package com.ty.HospitalManagementSystem.dao;

import com.ty.HospitalManagementSystem.dto.Hospital;
import com.ty.HospitalManagementSystem.repo.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class HospitalDao {

	@Autowired
	private HospitalRepo hospitalRepo;

	public Hospital savehospital(Hospital hospital) {
		return hospitalRepo.save(hospital);
	}

	public Optional<Hospital> updatehospital(int id, Hospital hospital) {
		return hospitalRepo.findById(id).map(existingHospital->
		{
			existingHospital.setName(hospital.getName());
			existingHospital.setEmail(hospital.getEmail());
			return hospitalRepo.save(existingHospital);

		});
	}

	public Optional<Hospital> deletehospital(int id) {
		return hospitalRepo.findById(id).map(hospital->{
			hospitalRepo.delete(hospital);
			return hospital;

		});
	}

	public Optional<Hospital> gethospitalbyid(int id) {
		return hospitalRepo.findById(id);
	}

	public Page<Hospital> getAllHospitals(Pageable pageable) {
		return hospitalRepo.findAll(pageable);
	}

	public Optional<Hospital> gethospitalbyemail(String email) {
		return Optional.ofNullable(hospitalRepo.findhospitalbyemail(email));
	}


}
