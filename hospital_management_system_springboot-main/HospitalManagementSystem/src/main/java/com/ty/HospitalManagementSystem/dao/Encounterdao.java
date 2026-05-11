package com.ty.HospitalManagementSystem.dao;

import com.ty.HospitalManagementSystem.Entity.Encounter;
import com.ty.HospitalManagementSystem.repo.EnconuterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Encounterdao {

	@Autowired
	private EnconuterRepo repo;

	public Encounter saveEncounter(Encounter encounter) {
		return repo.save(encounter);
	}

	public Encounter updateEncounter(int id, Encounter encounter) {
		return repo.findById(id)
				.map(db -> {
					encounter.setId(id);
					return repo.save(encounter);
				})
				.orElse(null);
	}

	public Encounter deleteEncounter(int id) {
		return repo.findById(id)
				.map(db -> {
					repo.delete(db);
					return db;
				})
				.orElse(null);
	}

	public Encounter getEncounterById(int id) {
		return repo.findById(id).orElse(null);
	}
}