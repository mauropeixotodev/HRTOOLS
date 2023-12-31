package com.hrtools.www.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Company_seq")
	@SequenceGenerator(name = "Company_seq", sequenceName = "Company_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String legalStructure;
	@Column(nullable = false)
	private String industry;
	@Column(nullable = false)
	private String headquartersAddress;
	@Column(nullable = false)
	private String registrationNumber;
	@Column(nullable = false)
	private String foundingDate;
	@Column(nullable = false)
	private String ceo;
	
	@OneToMany(mappedBy = "company")
	private List<Department> department;
	@OneToMany(mappedBy = "company")
	private List<Employee> employees;
	@Column(nullable = false)
	private Double annualRevenue;
	@Column(nullable = false)
	private String missionStatement;
	@Column(nullable = false)
	private String VisionStatement;
		
}
