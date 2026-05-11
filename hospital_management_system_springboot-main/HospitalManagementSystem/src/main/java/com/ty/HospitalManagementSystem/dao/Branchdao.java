package com.ty.HospitalManagementSystem.dao;

import com.ty.HospitalManagementSystem.Entity.Address;
import com.ty.HospitalManagementSystem.Entity.Branch;
import com.ty.HospitalManagementSystem.Entity.Hospital;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;
import com.ty.HospitalManagementSystem.repo.BranchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Branchdao {

	@Autowired
	private BranchRepo branchRepo;

	@Autowired
	private HospitalDao hospitalDao;

	@Autowired
	private Addressdao addressdao;

	// SAVE
	public Branch saveBranch(int hid, int aid, Branch branch) {

		Hospital hospital = hospitalDao.gethospitalbyid(hid);

		Address address = addressdao.getaddressbyid(aid);

		branch.setHospital(hospital);
		branch.setAddress(address);

		return branchRepo.save(branch);
	}

	// UPDATE
	public Branch updateBranch(int id, Branch branch) {

		Branch existing = branchRepo.findById(id)
				.orElseThrow(() ->
						new IdNotFoundException("Branch not found for id " + id));

		branch.setId(existing.getId());
		branch.setHospital(existing.getHospital());
		branch.setAddress(existing.getAddress());

		return branchRepo.save(branch);
	}

	// DELETE
	public Branch deleteBranch(int id) {

		Branch branch = branchRepo.findById(id)
				.orElseThrow(() ->
						new IdNotFoundException("Branch not found for id " + id));

		branchRepo.delete(branch);
		return branch;
	}

	// GET BY ID
	public Branch getbranchbyid(int id) {

		return branchRepo.findById(id)
				.orElseThrow(() ->
						new IdNotFoundException("Branch not found for id " + id));
	}

	// GET BY HOSPITAL ID
	public List<Branch> getbranchbyhospitalid(int hid) {

		return branchRepo.findByHospital(hid);
	}
}