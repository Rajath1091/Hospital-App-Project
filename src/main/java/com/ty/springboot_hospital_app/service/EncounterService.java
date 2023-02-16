package com.ty.springboot_hospital_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springboot_hospital_app.dao.BranchDao;
import com.ty.springboot_hospital_app.dao.EncounterDao;
import com.ty.springboot_hospital_app.dao.PersonDao;
import com.ty.springboot_hospital_app.dto.Branch;
import com.ty.springboot_hospital_app.dto.Encounter;
import com.ty.springboot_hospital_app.dto.Person;
import com.ty.springboot_hospital_app.service.exception.IdNotFoundException;
import com.ty.springboot_hospital_app.util.ResponseStructure;

@Service
public class EncounterService {

	@Autowired
	private EncounterDao dao;
	@Autowired
	private PersonDao personDao;
	@Autowired
	private BranchDao branchDao;

	public ResponseEntity<ResponseStructure<Encounter>> saveEncounter(Encounter encounter, int pid, int bid) {
		Person person = personDao.getPersonById(pid);
		Branch branch = branchDao.getBranchById(bid);

		encounter.setPerson(person);
		List<Branch> branchs = new ArrayList<>();
		branchs.add(branch);
		encounter.setBranchs(branchs);

		ResponseStructure<Encounter> structure = new ResponseStructure<>();
		structure.setMessage("Saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveEncounter(encounter));

		return new ResponseEntity<ResponseStructure<Encounter>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Encounter>> updateEncounter(int id, Encounter encounter, int bid) {
		Encounter dbEncounter = dao.getEncounterById(id);
		Branch branch = branchDao.getBranchById(bid);

		List<Branch> branchs = dbEncounter.getBranchs();
		branchs.add(branch);
		encounter.setBranchs(branchs);
		encounter.setPerson(dbEncounter.getPerson());

		ResponseStructure<Encounter> structure = new ResponseStructure<>();
		structure.setMessage("Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(dao.updateEncounter(id, encounter));

		return new ResponseEntity<ResponseStructure<Encounter>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Encounter>> deleteEncounter(int id) {
		Encounter encounter = dao.deleteEncounterById(id);
		if (encounter != null) {
			ResponseStructure<Encounter> structure = new ResponseStructure<>();
			structure.setMessage("Deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(encounter);

			return new ResponseEntity<ResponseStructure<Encounter>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id not found for Encounter");
		}
	}

	public ResponseEntity<ResponseStructure<Encounter>> getEncounter(int id) {
		Encounter encounter = dao.getEncounterById(id);
		if (encounter != null) {
			ResponseStructure<Encounter> structure = new ResponseStructure<>();
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(encounter);

			return new ResponseEntity<ResponseStructure<Encounter>>(structure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Id not found for Encounter");
		}
	}

}
