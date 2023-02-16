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

import com.ty.springboot_hospital_app.dto.Hospital;
import com.ty.springboot_hospital_app.service.HospitalService;
import com.ty.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class HospitalController {

	@Autowired
	private HospitalService service;

	@ApiOperation(value = "Save Hospital", notes = "Api is used to save hospital")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully saved") })
	@PostMapping("/hospital")
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(@Valid @RequestBody Hospital hospital) {
		return service.saveHospital(hospital);
	}

	@ApiOperation(value = "Update Hospital", notes = "Api is used to update hospital using hospital_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Id not found for hospital") })
	@PutMapping("/hospital")
	public ResponseEntity<ResponseStructure<Hospital>> updateHospital(@Valid @RequestParam int id,
			@RequestBody Hospital hospital) {
		return service.updateHospital(id, hospital);
	}

	@ApiOperation(value = "Delete Hospital", notes = "Api is used to delete hospital using hospital_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Id not found for hospital") })
	@DeleteMapping("/hospital")
	public ResponseEntity<ResponseStructure<Hospital>> deleteHospital(@Valid @RequestParam int id) {
		return service.deleteHospital(id);
	}

	@ApiOperation(value = "Get Hospital By Id", notes = "Api is used to fetch hospital using hospital_id")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully found"),
			@ApiResponse(code = 404, message = "Id not found for hospital") })
	@GetMapping("/hospital")
	public ResponseEntity<ResponseStructure<Hospital>> getHospital(@Valid @RequestParam int id) {
		return service.getHospitalById(id);
	}

}
