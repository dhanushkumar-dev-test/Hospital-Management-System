package com.ty.HospitalManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Schema(description = "Address Entity")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Address Id", example = "101")
	private int id;

	@NotNull(message = "state cannot be null")
	@Schema(description = "State name", example = "Maharashtra")
	private String state;

	@NotNull(message = "city cannot be null")
	@Schema(description = "City name", example = "Mumbai")
	private String city;

	@NotNull(message = "pincode cannot be null")
	@Schema(description = "Postal code", example = "400001")
	private long pincode;
}