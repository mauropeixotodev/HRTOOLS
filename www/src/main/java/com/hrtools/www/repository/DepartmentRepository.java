package com.hrtools.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrtools.www.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
