package com.hrtools.www.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Employee_seq")
	@SequenceGenerator(name = "Employee_seq", sequenceName = "Employee_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;
	@Column(nullable = false)
	private String fullname;
	@Column(nullable = false)
	private String emailAddress;
	@Column(nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Employee manager;

	@OneToMany(mappedBy = "manager")
	private List<Employee> directReports;
	@ManyToOne
	@JoinColumn(name = "position_id")
	private Position position;
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	@Column(nullable = false)
	private Date dateOfJoining;

	private Date dateOfTermination;
	@Column(nullable = false)
	private String phoneNumeber;
	@Column(nullable = false)
	private String employeeType;
	@Column(nullable = false)
	private String activityStatus;
	@ManyToMany(mappedBy = "employees")
	private List<Notification> notification;

	private String roles;

}
