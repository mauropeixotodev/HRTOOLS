package com.hrtools.www.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Employee implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Employee_seq")
	@SequenceGenerator(name = "Employee_seq", sequenceName = "Employee_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;

	
	private String fullname;

	private String emailAddress;
	@Column(nullable = false)
	private String username;
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
	
	private Date dateOfJoining;

	private Date dateOfTermination;

	private String phoneNumeber;
	
	private String employeeType;
	@Column(nullable = false)
	private boolean activityStatus;
	@ManyToMany(mappedBy = "employees")
	private List<Notification> notification;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employee_roles", joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	
	
	
	   @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        for (Role role : this.roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
	        return authorities;
	    }
	    


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.activityStatus;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.activityStatus;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.activityStatus;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.activityStatus;
	}

}
