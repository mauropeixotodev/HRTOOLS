package com.hrtools.www.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Department_seq")
	@SequenceGenerator(name = "Department_seq", sequenceName = "Department_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	@OneToOne
	@JoinColumn(name = "owner_id")
	private Employee owner;

	@OneToMany(mappedBy = "department")
	private List<Employee> employees;
	
	@OneToMany(mappedBy = "department")
	private List<Position> positions;
	@Column(nullable = false)
	private Double budget;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String phoneNumber;
	@Column(nullable = false)
	private Date dateOfestablishment;

}
