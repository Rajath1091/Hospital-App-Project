package com.ty.springboot_hospital_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springboot_hospital_app.dto.Hospital;

public interface HospitalRepo extends JpaRepository<Hospital, Integer> {

	public Hospital findByEmail(String email);

}
