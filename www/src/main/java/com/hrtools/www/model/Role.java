package com.hrtools.www.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

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
public class Role implements GrantedAuthority {
 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Role_seq")
	@SequenceGenerator(name = "Role_seq", sequenceName = "Role_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<Employee> employees;

    @ManyToMany
    @JoinTable(
        name = "roles_privileges", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.name;
	}
}