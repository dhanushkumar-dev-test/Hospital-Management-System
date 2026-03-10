package com.ty.HospitalManagementSystem.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Hospital name is required")
	@Column(name = "hospital_name", nullable = false)
	private String name;
	@NotBlank(message = "email is mandatory")
	private String email;


}
