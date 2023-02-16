package com.ty.springboot_hospital_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springboot_hospital_app.dao.BranchDao;
import com.ty.springboot_hospital_app.dto.Branch;
import com.ty.springboot_hospital_app.service.exception.IdNotFoundException;
import com.ty.springboot_hospital_app.util.ResponseStructure;

@Service
public class BranchService {

	@Autowired
	private BranchDao dao;

	public ResponseEntity<ResponseStructure<Branch>> saveBranch(Branch branch, int hid, int aid) {
		ResponseStructure<Branch> structure = new ResponseStructure<>();
		structure.setMessage("Successfully Saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveBranch(branch, hid, aid));
		return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Branch>> updateBranch(int id, Branch branch) {
		Branch dbBranch = dao.updateBranch(id, branch);
		if (dbBranch != null) {
			ResponseStructure<Branch> structure = new ResponseStructure<>();
			structure.setMessage("Successfylly Updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbBranch);
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id not found for Branch");
		}
	}

	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(int id) {
		Branch branch = dao.deleteBranch(id);
		if (branch != null) {
			ResponseStructure<Branch> structure = new ResponseStructure<>();
			structure.setMessage("Successfylly Deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(branch);
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id not found for Branch");
		}
	}

	public ResponseEntity<ResponseStructure<Branch>> getBranchById(int id) {
		Branch branch = dao.getBranchById(id);
		if (branch != null) {
			ResponseStructure<Branch> structure = new ResponseStructure<>();
			structure.setMessage("Successfully Found");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(branch);
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id not found for Branch");
		}
	}

	public ResponseEntity<ResponseStructure<List<Branch>>> getBranchByHospitalId(int hid) {
		ResponseStructure<List<Branch>> structure = new ResponseStructure<>();
		structure.setMessage("Successfully Found");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(dao.getBranchByHospitalId(hid));
		return new ResponseEntity<ResponseStructure<List<Branch>>>(structure, HttpStatus.OK);
	}

}
