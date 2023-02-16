package com.ty.springboot_hospital_app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.springboot_hospital_app.dto.MedOrder;
import com.ty.springboot_hospital_app.service.MedOrderService;
import com.ty.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class MedOrderController {

	@Autowired
	private MedOrderService service;

	@ApiOperation(value = "Save MedOrder", notes = "Api is used to save medorder using encounter_id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully saved"),
			@ApiResponse(code = 404, message = "Id not found for encounter") })
	@PostMapping("/medorder")
	public ResponseEntity<ResponseStructure<MedOrder>> saveMedOrder(@Valid @RequestBody MedOrder medOrder,
			@RequestParam int eid) {
		return service.saveMedOrder(medOrder, eid);
	}

	@ApiOperation(value = "Update MedOrder", notes = "Api is used to update medorder using medorder_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Id not found for medorder") })
	@PutMapping("/medorder")
	public ResponseEntity<ResponseStructure<MedOrder>> updateMedOrder(@Valid @RequestParam int id,
			@RequestBody MedOrder medOrder) {
		return service.updateMedOrder(id, medOrder);
	}

	@ApiOperation(value = "Get MedOrder By Id", notes = "Api is used to fetch medorder using medorder_id")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully found"),
			@ApiResponse(code = 404, message = "Id not found for medorder") })
	@GetMapping("/medorder")
	public ResponseEntity<ResponseStructure<MedOrder>> getMedOrderById(@Valid @RequestParam int id) {
		return service.getMedOrderById(id);
	}

	@ApiOperation(value = "Delete MedOrder", notes = "Api is used to delete medorder using medorder_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Id not found for medorder") })
	@DeleteMapping("/medorder")
	public ResponseEntity<ResponseStructure<MedOrder>> deleteMedOrder(@Valid @RequestParam int id) {
		return service.deleteMedOrder(id);
	}

}
