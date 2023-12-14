package com.hrtools.www.controller.endPoint;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.hrtools.www.controller.request.EmployeeRequest;
import com.hrtools.www.controller.response.EmployeeResponse;
import com.hrtools.www.service.EmployeeService;

import javax.persistence.EntityNotFoundException;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee")
	public List<EmployeeResponse> listEmployees() throws Exception {
		return employeeService.listEmployees();
	}

	@PostMapping("/employee")
	public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody @Validated EmployeeRequest employeeRequest,
			UriComponentsBuilder uriBuilder) throws Exception {
		EmployeeResponse employee = employeeService.createEmployee(employeeRequest);
		URI uri = uriBuilder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri();
		return ResponseEntity.created(uri).body(employee);
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody @Validated EmployeeRequest employeeRequest,
			@PathVariable Long id) throws Exception {
		try {
			return ResponseEntity.ok(employeeService.updateEmployee(employeeRequest, id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<EmployeeResponse> findEmployee(@PathVariable Long id) throws Exception {
		try {
			return ResponseEntity.ok(employeeService.findEmployee(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
