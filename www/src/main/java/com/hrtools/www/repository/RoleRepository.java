package com.hrtools.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrtools.www.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
