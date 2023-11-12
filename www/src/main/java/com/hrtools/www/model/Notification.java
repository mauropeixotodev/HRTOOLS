package com.hrtools.www.model;

import java.util.List;
import jakarta.persistence.*;
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
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Notification_seq")
	@SequenceGenerator(name = "Notification_seq", sequenceName = "Notification_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private long id;
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	@ManyToMany()
	@JoinTable(name = "notification_employee", joinColumns = @JoinColumn(name = "notification_id"), inverseJoinColumns = @JoinColumn(name = "employees_id"))
	private List<Employee> employees;

}
