package com.ty.springboot_hospital_app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.springboot_hospital_app.dto.Branch;
import com.ty.springboot_hospital_app.service.BranchService;
import com.ty.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BranchController {

	@Autowired
	private BranchService service;

	@ApiOperation(value = "Save Branch", notes = "Api is used to save branch using hospital_id & address_id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully saved"),
			@ApiResponse(code = 404, message = "Id not found for hospital or address") })
	@PostMapping("/branch/{hid}/{aid}")
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@Valid @RequestBody Branch branch,
			@PathVariable int hid, @PathVariable int aid) {
		return service.saveBranch(branch, hid, aid);
	}

	@ApiOperation(value = "Update Branch", notes = "Api is used to update branch using branch_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Updated"),
			@ApiResponse(code = 404, message = "Id not found for branch") })
	@PutMapping("/branch")
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(@Valid @RequestParam int id,
			@RequestBody Branch branch) {
		return service.updateBranch(id, branch);
	}

	@ApiOperation(value = "Delete Branch", notes = "Api is used to delete branch using branch_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Deleted"),
			@ApiResponse(code = 404, message = "Id not found for branch") })
	@DeleteMapping("/branch")
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(@Valid @RequestParam int id) {
		return service.deleteBranch(id);
	}

	@ApiOperation(value = "Get Branch By Id", notes = "Api is used to fetch branch using branch_id")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully Found"),
			@ApiResponse(code = 404, message = "Id not found for branch") })
	@GetMapping("/branch")
	public ResponseEntity<ResponseStructure<Branch>> getBranchById(@Valid @RequestParam int id) {
		return service.getBranchById(id);
	}

	@ApiOperation(value = "Get Branch By Hospital Id", notes = "Api is used to fetch branch using hospital_id")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully Found"),
			@ApiResponse(code = 404, message = "Id not found for hospital") })
	@GetMapping("/branch/{hid}")
	public ResponseEntity<ResponseStructure<List<Branch>>> getBranchByHospitalId(@Valid @PathVariable int hid) {
		return service.getBranchByHospitalId(hid);
	}

}
