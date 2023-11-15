package com.hrtools.www.model;

import java.util.Date;
import java.util.List;

<<<<<<< HEAD
=======
import org.hibernate.annotations.CreationTimestamp;

>>>>>>> master
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Position_seq")
	@SequenceGenerator(name = "Position_seq", sequenceName = "Position_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	@OneToMany(mappedBy = "position")
	private List<Employee> reportsTo;
	@Column(nullable = false)
	private Double salaryRange;
	@Column(nullable = false)
<<<<<<< HEAD
	private String Qualifications;
=======
	private String qualifications;
>>>>>>> master
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private String employmentType;
<<<<<<< HEAD
	@Column(nullable = false)
=======
	@CreationTimestamp
>>>>>>> master
	private Date dateOfCreation;

}
