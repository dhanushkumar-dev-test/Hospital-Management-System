package com.ty.HospitalManagementSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "encounter")
public class Encounter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String reason;

	private double cost;

	@ManyToOne(fetch = FetchType.EAGER)
	private Person person;

	@ManyToOne(fetch = FetchType.EAGER)
	private Branch branch;
}