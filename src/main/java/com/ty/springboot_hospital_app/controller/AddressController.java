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

import com.ty.springboot_hospital_app.dto.Address;
import com.ty.springboot_hospital_app.service.AddressService;
import com.ty.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class AddressController {

	@Autowired
	private AddressService service;

	@ApiOperation(value = "Save Address", notes = "Api is used to save address")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully saved") })
	@PostMapping("/address")
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@Valid @RequestBody Address address) {
		return service.saveAddress(address);
	}

	@ApiOperation(value = "Update Address", notes = "Api is used to update address using address_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Id not found for address") })
	@PutMapping("/address")
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@Valid @RequestParam int id,
			@RequestBody Address address) {
		return service.updateAddress(id, address);
	}

	@ApiOperation(value = "Delete Address", notes = "Api is used to delete address using address_id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Id not found for address") })
	@DeleteMapping("/address")
	public ResponseEntity<ResponseStructure<Address>> deleteAddress(@Valid @RequestParam int id) {
		return service.deleteAddress(id);
	}

	@ApiOperation(value = "Get Address By Id", notes = "Api is used to fetch address using address_id")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully found"),
			@ApiResponse(code = 404, message = "Id not found for address") })
	@GetMapping("/address")
	public ResponseEntity<ResponseStructure<Address>> getAddressById(@Valid @RequestParam int id) {
		return service.getAddressById(id);
	}

}
