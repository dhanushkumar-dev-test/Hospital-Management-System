package com.ty.HospitalManagementSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "state cannot be blank")
	@Column(nullable = false)
	private String state;

	@NotBlank(message = "city cannot be blank")
	@Column(nullable = false)
	private String city;

	@NotNull(message = "pincode cannot be null")
	private long pincode;
}