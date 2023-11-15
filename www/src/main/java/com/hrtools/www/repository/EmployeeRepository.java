package com.hrtools.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrtools.www.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
