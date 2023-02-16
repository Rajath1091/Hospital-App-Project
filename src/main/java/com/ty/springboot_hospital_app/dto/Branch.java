package com.ty.springboot_hospital_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "name can't be blank")
	private String name;
	@NotNull(message = "phone can't be blank")
	@Pattern(regexp = "[6-9][0-9]{9}")
	private long phone;
	@NotBlank(message = "manager can't be blank")
	private String manager;
	@ManyToOne
	private Hospital hospital;
	@OneToOne
	private Address address;

}
