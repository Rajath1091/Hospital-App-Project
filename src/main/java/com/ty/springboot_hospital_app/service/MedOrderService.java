package com.ty.springboot_hospital_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springboot_hospital_app.dao.MedOrderDao;
import com.ty.springboot_hospital_app.dto.MedOrder;
import com.ty.springboot_hospital_app.service.exception.IdNotFoundException;
import com.ty.springboot_hospital_app.util.ResponseStructure;

@Service
public class MedOrderService {

	@Autowired
	private MedOrderDao dao;

	public ResponseEntity<ResponseStructure<MedOrder>> saveMedOrder(MedOrder medOrder, int eid) {
		ResponseStructure<MedOrder> structure = new ResponseStructure<>();
		structure.setMessage("Saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveMedOrder(medOrder, eid));
		return new ResponseEntity<ResponseStructure<MedOrder>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<MedOrder>> updateMedOrder(int id, MedOrder medOrder) {
		MedOrder dbMedOrder = dao.getMedOrderById(id);
		medOrder.setEncounter(dbMedOrder.getEncounter());
		MedOrder daoMedOrder = dao.updateMedOrder(id, medOrder);
		ResponseStructure<MedOrder> structure = new ResponseStructure<>();
		structure.setMessage("Updated");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(daoMedOrder);
		return new ResponseEntity<ResponseStructure<MedOrder>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<MedOrder>> getMedOrderById(int id) {
		MedOrder medOrder = dao.getMedOrderById(id);
		if (medOrder != null) {
			ResponseStructure<MedOrder> structure = new ResponseStructure<>();
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(medOrder);
			return new ResponseEntity<ResponseStructure<MedOrder>>(structure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Id not found for MedOrder");
		}
	}

	public ResponseEntity<ResponseStructure<MedOrder>> deleteMedOrder(int id) {
		MedOrder medOrder = dao.deleteMedOrder(id);
		if (medOrder != null) {
			ResponseStructure<MedOrder> structure = new ResponseStructure<>();
			structure.setMessage("Deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(medOrder);
			return new ResponseEntity<ResponseStructure<MedOrder>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id not found for MedOrder");
		}
	}

}
