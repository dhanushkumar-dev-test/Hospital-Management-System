package com.ty.HospitalManagementSystem.repo;

import com.ty.HospitalManagementSystem.Entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BranchRepo extends JpaRepository<Branch, Integer>{

	@Query("select b from Branch b where b.hospital.id=?1")
	List<Branch> findByHospital(int hid);

		
	
}

