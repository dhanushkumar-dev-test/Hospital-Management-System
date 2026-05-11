package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.dao.Branchdao;
import com.ty.HospitalManagementSystem.dao.Encounterdao;
import com.ty.HospitalManagementSystem.dao.Persondao;
import com.ty.HospitalManagementSystem.Entity.Branch;
import com.ty.HospitalManagementSystem.Entity.Encounter;
import com.ty.HospitalManagementSystem.Entity.Person;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncounterService {

	@Autowired
	private Encounterdao encounterdao;

	@Autowired
	private Persondao persondao;

	@Autowired
	private Branchdao branchdao;

	// CREATE
	public Encounter saveEncounter(Encounter encounter, int pid, int bid) {

		Person person = persondao.getPersonById(pid);

		if (person == null) {
			throw new IdNotFoundException("Person not found for id " + pid);
		}

		Branch branch = branchdao.getbranchbyid(bid);

		if (branch == null) {
			throw new IdNotFoundException("Branch not found for id " + bid);
		}

		encounter.setPerson(person);

		encounter.setBranch(branch);

		return encounterdao.saveEncounter(encounter);
	}

	// UPDATE
	public Encounter updateEncounter(int id, Encounter encounter, int bid) {

		Encounter dbEncounter = encounterdao.getEncounterById(id);

		if (dbEncounter == null) {
			throw new IdNotFoundException("Encounter not found for id " + id);
		}

		Branch branch = branchdao.getbranchbyid(bid);

		if (branch == null) {
			throw new IdNotFoundException("Branch not found for id " + bid);
		}

		encounter.setId(id);

		// Preserve existing person
		encounter.setPerson(dbEncounter.getPerson());

		// Set updated branch
		encounter.setBranch(branch);

		return encounterdao.updateEncounter(id, encounter);
	}

	// DELETE
	public Encounter deleteEncounter(int id) {

		Encounter encounter = encounterdao.deleteEncounter(id);

		if (encounter == null) {
			throw new IdNotFoundException("Encounter not found for id " + id);
		}

		return encounter;
	}

	// GET
	public Encounter getEncounterById(int id) {

		Encounter encounter = encounterdao.getEncounterById(id);

		if (encounter == null) {
			throw new IdNotFoundException("Encounter not found for id " + id);
		}

		return encounter;
	}
}