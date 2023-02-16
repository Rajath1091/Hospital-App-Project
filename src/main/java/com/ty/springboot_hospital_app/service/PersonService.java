package com.ty.springboot_hospital_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springboot_hospital_app.dao.PersonDao;
import com.ty.springboot_hospital_app.dto.Person;
import com.ty.springboot_hospital_app.service.exception.IdNotFoundException;
import com.ty.springboot_hospital_app.util.ResponseStructure;

@Service
public class PersonService {

	@Autowired
	private PersonDao dao;

	public ResponseEntity<ResponseStructure<Person>> savePerson(Person person) {
		ResponseStructure<Person> structure = new ResponseStructure<>();
		structure.setMessage("Successfully Saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.savePerson(person));
		return new ResponseEntity<ResponseStructure<Person>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Person>> updatePerson(int id, Person person) {
		Person dbPerson = dao.updatePerson(id, person);
		if (dbPerson != null) {
			ResponseStructure<Person> structure = new ResponseStructure<>();
			structure.setMessage("Successfully Updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbPerson);
			return new ResponseEntity<ResponseStructure<Person>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id not found for Person");
		}
	}

	public ResponseEntity<ResponseStructure<Person>> deletePerson(int id) {
		Person person = dao.deletePerson(id);
		if (person != null) {
			ResponseStructure<Person> structure = new ResponseStructure<>();
			structure.setMessage("Successfully Deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(person);
			return new ResponseEntity<ResponseStructure<Person>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id not found for Person");
		}
	}

	public ResponseEntity<ResponseStructure<Person>> getPersonById(int id) {
		Person person = dao.getPersonById(id);
		if (person != null) {
			ResponseStructure<Person> structure = new ResponseStructure<>();
			structure.setMessage("Successfully Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(person);
			return new ResponseEntity<ResponseStructure<Person>>(structure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Id not found for Person");
		}
	}

}
