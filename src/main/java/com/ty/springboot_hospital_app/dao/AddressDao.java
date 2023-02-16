package com.ty.springboot_hospital_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springboot_hospital_app.dto.Address;
import com.ty.springboot_hospital_app.repo.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo repo;

	public Address saveAddress(Address address) {
		return repo.save(address);
	}

	public Address updateAddress(int id, Address address) {
		if (repo.findById(id).isPresent()) {
			address.setId(id);
			return repo.save(address);
		} else {
			return null;
		}
	}

	public Address deleteAddress(int id) {
		if (repo.findById(id).isPresent()) {
			Address address = repo.findById(id).get();
			repo.deleteById(id);
			return address;
		} else {
			return null;
		}
	}

	public Address getAddressById(int id) {
		if (repo.findById(id).isPresent()) {
			return repo.findById(id).get();
		} else {
			return null;
		}
	}

}
