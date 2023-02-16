package com.ty.springboot_hospital_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springboot_hospital_app.dto.Encounter;
import com.ty.springboot_hospital_app.dto.MedOrder;
import com.ty.springboot_hospital_app.repo.MedOrderRepo;

@Repository
public class MedOrderDao {

	@Autowired
	private MedOrderRepo repo;
	@Autowired
	private EncounterDao dao;

	public MedOrder saveMedOrder(MedOrder medOrder, int eid) {
		Encounter encounter = dao.getEncounterById(eid);
		medOrder.setEncounter(encounter);
		return repo.save(medOrder);
	}

	public MedOrder updateMedOrder(int id, MedOrder medOrder) {
		if (repo.findById(id).isPresent()) {
			medOrder.setId(id);
			return repo.save(medOrder);
		} else {
			return null;
		}
	}

	public MedOrder getMedOrderById(int id) {
		return repo.findById(id).get();
	}

	public MedOrder deleteMedOrder(int id) {
		if (repo.findById(id).isPresent()) {
			MedOrder medOrder = repo.findById(id).get();
			repo.deleteById(id);
			return medOrder;
		} else {
			return null;
		}
	}

}
