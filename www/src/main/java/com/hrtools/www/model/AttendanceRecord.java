package com.hrtools.www.model;

import java.util.Date;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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

	private Date clockIn;

	private Date clockOut;
	
	@Column(nullable = false)
	private int status;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

}
