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

import com.ty.springboot_hospital_app.dto.Encounter;
import com.ty.springboot_hospital_app.service.EncounterService;
import com.ty.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class EncounterController {

	@Autowired
	private EncounterService service;

	@ApiOperation(value = "Save Encounter", notes = "Api is used to save encounter using person_id & branch_id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully saved"),
			@ApiResponse(code = 404, message = "Id not found for person or branch") })
	@PostMapping("/encounter")
	public ResponseEntity<ResponseStructure<Encounter>> saveEncounter(@Valid @RequestBody Encounter encounter,
			@RequestParam int pid, @RequestParam int bid) {
		return service.saveEncounter(encounter, pid, bid);
	}

	@ApiOperation(value = "Update Encounter", notes = "Api is used to update encounter using encounter_id & branch_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Id not found for encounter or branch") })
	@PutMapping("/encounter")
	public ResponseEntity<ResponseStructure<Encounter>> updateEncounter(@Valid @RequestParam int id,
			@RequestBody Encounter encounter, @RequestParam int bid) {
		return service.updateEncounter(id, encounter, bid);
	}

	@ApiOperation(value = "Delete Encounter", notes = "Api is used to delete encounter using encounter_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Id not found for encounter") })
	@DeleteMapping("/encounter")
	public ResponseEntity<ResponseStructure<Encounter>> deleteEncounter(@Valid @RequestParam int id) {
		return service.deleteEncounter(id);
	}

	@ApiOperation(value = "Get Encounter By Id", notes = "Api is used to fetch encounter using encounter_id")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully found"),
			@ApiResponse(code = 404, message = "Id not found for encounter") })
	@GetMapping("/encounter")
	public ResponseEntity<ResponseStructure<Encounter>> getEncounter(@Valid @RequestParam int id) {
		return service.getEncounter(id);
	}

}
