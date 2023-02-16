package com.ty.springboot_hospital_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springboot_hospital_app.dto.MedItems;

public interface MedItemsRepo extends JpaRepository<MedItems, Integer> {

}
