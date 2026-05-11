package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.dao.Branchdao;
import com.ty.HospitalManagementSystem.Entity.Branch;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

	@Autowired
	private Branchdao branchdao;

	// SAVE
	public Branch saveBranch(int hid, int aid, Branch branch) {

		return branchdao.saveBranch(hid, aid, branch);
	}

	// UPDATE
	public Branch updateBranch(int id, Branch branch) {

		return branchdao.updateBranch(id, branch);
	}

	// DELETE
	public Branch deleteBranch(int id) {

		return branchdao.deleteBranch(id);
	}

	// GET BY ID
	public Branch getbranchbyid(int id) {

		return branchdao.getbranchbyid(id);
	}

	// GET BY HOSPITAL ID
	public List<Branch> getbranchbyhospitalid(int hid) {

		List<Branch> branches =
				branchdao.getbranchbyhospitalid(hid);

		if (branches != null && !branches.isEmpty()) {
			return branches;
		}

		throw new IdNotFoundException(
				"No branches found for Hospital ID " + hid);
	}
}