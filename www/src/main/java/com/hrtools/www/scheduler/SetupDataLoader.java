package com.hrtools.www.scheduler;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hrtools.www.model.Employee;
import com.hrtools.www.model.Privilege;
import com.hrtools.www.model.Role;
import com.hrtools.www.repository.EmployeeRepository;
import com.hrtools.www.repository.PrivilegeRepository;
import com.hrtools.www.repository.RoleRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

		List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
		createRoleIfNotFound("ROLE_STAFF", Arrays.asList(readPrivilege));

		Employee employee = employeeRepository.save(Employee.builder().username("admin").fullname("Admin").password(new BCryptPasswordEncoder().encode("admin")).activityStatus(true)
				.roles(Arrays.asList(roleRepository.findByName("ROLE_STAFF"))).build());
		
		System.out.println("Numero de roles: "+roleRepository.count());
		employee.getRoles().forEach(e -> System.out.println(e));

		alreadySetup = true;
	}

	@Transactional
	Privilege createPrivilegeIfNotFound(String name) {

		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = Privilege.builder().name(name).build();
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = Role.builder().name(name).build();
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}
	
	
	
}