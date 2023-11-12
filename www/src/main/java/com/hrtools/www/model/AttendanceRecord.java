package com.hrtools.www.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class AttendanceRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AttendanceRecord_seq")
	@SequenceGenerator(name = "AttendanceRecord_seq", sequenceName = "AttendanceRecord_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;
	@Column(nullable = false)
	private Date dayDate;

	@Column(nullable = false)
	private Date clockIn;

	private Date clockOut;
	@Column(nullable = false)
	private String Status;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

}
