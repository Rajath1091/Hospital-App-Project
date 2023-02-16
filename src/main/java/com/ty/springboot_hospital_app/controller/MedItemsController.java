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

import com.ty.springboot_hospital_app.dto.MedItems;
import com.ty.springboot_hospital_app.service.MedItemsService;
import com.ty.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class MedItemsController {

	@Autowired
	private MedItemsService service;

	@ApiOperation(value = "Save MedItems", notes = "Api is used to save meditems using medorder_id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully saved"),
			@ApiResponse(code = 404, message = "Id not found for medorder") })
	@PostMapping("/meditems")
	public ResponseEntity<ResponseStructure<MedItems>> saveMedItems(@Valid @RequestBody MedItems medItems,
			@RequestParam int mid) {
		return service.saveMedItems(medItems, mid);
	}

	@ApiOperation(value = "Update MedItems", notes = "Api is used to update meditems using meditems_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Id not found for meditems") })
	@PutMapping("/meditems")
	public ResponseEntity<ResponseStructure<MedItems>> updateMedItems(@Valid @RequestParam int id,
			@RequestBody MedItems medItems) {
		return service.updateMedItems(id, medItems);
	}

	@ApiOperation(value = "Delete MedItems", notes = "Api is used to delete meditems using meditems_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Id not found for meditems") })
	@GetMapping("/meditems")
	public ResponseEntity<ResponseStructure<MedItems>> getMedItemsById(@Valid @RequestParam int id) {
		return service.getMedItemsById(id);
	}

	@ApiOperation(value = "Get MedItems By Id", notes = "Api is used to fetch meditems using meditems_id")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully found"),
			@ApiResponse(code = 404, message = "Id not found for meditems") })
	@DeleteMapping("/meditems")
	public ResponseEntity<ResponseStructure<MedItems>> deleteMedItems(@Valid @RequestParam int id) {
		return service.deleteMedItems(id);
	}

}
