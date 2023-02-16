package com.ty.springboot_hospital_app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springboot_hospital_app.dto.Address;
import com.ty.springboot_hospital_app.dto.Branch;
import com.ty.springboot_hospital_app.dto.Hospital;
import com.ty.springboot_hospital_app.repo.BranchRepo;

@Repository
public class BranchDao {

	@Autowired
	private BranchRepo repo;
	@Autowired
	private HospitalDao dao;
	@Autowired
	private AddressDao addressDao;

	public Branch saveBranch(Branch branch, int hid, int aid) {
		Hospital hospital = dao.getHospitalById(hid);
		branch.setHospital(hospital);
		Address address = addressDao.getAddressById(aid);
		branch.setAddress(address);
		return repo.save(branch);
	}

	public Branch updateBranch(int id, Branch branch) {
		Branch dbBranch = repo.findById(id).get();
		if (dbBranch != null) {
			branch.setId(id);
			branch.setHospital(dbBranch.getHospital());
			branch.setAddress(dbBranch.getAddress());
			return repo.save(branch);
		} else {
			return null;
		}
	}

	public Branch deleteBranch(int id) {
		if (repo.findById(id).isPresent()) {
			Branch branch = repo.findById(id).get();
			repo.deleteById(id);
			return branch;
		} else {
			return null;
		}
	}

	public Branch getBranchById(int id) {
		if (repo.findById(id).isPresent()) {
			return repo.findById(id).get();
		} else {
			return null;
		}
	}

	public List<Branch> getBranchByHospitalId(int hid) {
		Hospital hospital = dao.getHospitalById(hid);
		return repo.findBranchByHospital(hospital);
	}

}
