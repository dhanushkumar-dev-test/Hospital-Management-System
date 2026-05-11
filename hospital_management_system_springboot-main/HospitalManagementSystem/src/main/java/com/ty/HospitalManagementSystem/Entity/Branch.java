package com.ty.HospitalManagementSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "branch")
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "name cannot be blank")
	@Column(nullable = false)
	private String name;

	@Min(6000000000L)
	@Max(9999999999L)
	private long phone;

	@NotBlank(message = "manager cannot be blank")
	@Column(nullable = false)
	private String manager;

	@ManyToOne(fetch = FetchType.EAGER)
	private Hospital hospital;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
	private Address address;
}