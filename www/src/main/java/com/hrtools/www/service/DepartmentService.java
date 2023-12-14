package com.hrtools.www.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrtools.www.controller.request.DepartmentRequest;
import com.hrtools.www.controller.response.DepartmentResponse;
import com.hrtools.www.etl.DepartmentETL;
import com.hrtools.www.model.Company;
import com.hrtools.www.model.Department;
import com.hrtools.www.repository.CompanyRepository;
import com.hrtools.www.repository.DepartmentRepository;
import javax.transaction.Transactional;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private CompanyRepository companyRepository;

	public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) throws Exception {
		try {
			// Logic to get Company and Owner objects should be added here
			Company company = null;
			Optional<Company> companyOpitional = companyRepository.findById(departmentRequest.getCompany());
			if(companyOpitional.isPresent()) {
				company = companyOpitional.get();
			}
			return DepartmentETL.convertDepartmentToDepartmentResponse(departmentRepository.save(DepartmentETL
					.convertDepartmentRequestToDepartment(departmentRequest, new Department(), company, null)), true);
		} catch (Exception e) {
			throw new Exception("Method: createDepartment | Reason: " + e.getMessage(), e);
		}
	}

	@Transactional
	public DepartmentResponse updateDepartment(DepartmentRequest departmentRequest, Long id) throws Exception {
		try {
			Optional<Department> departmentOptional = departmentRepository.findById(id);
			if (departmentOptional.isPresent()) {
				Department department = departmentOptional.get();
				// Logic to get Company and Owner objects should be added here
				Company company = null;
				Optional<Company> companyOpitional = companyRepository.findById(departmentRequest.getCompany());
				if(companyOpitional.isPresent()) {
					company = companyOpitional.get();
				}
				DepartmentETL.convertDepartmentRequestToDepartment(departmentRequest, department, company, null);
				return DepartmentETL.convertDepartmentToDepartmentResponse(department, true);
			}
			throw new Exception("Department not found");
		} catch (Exception e) {
			throw new Exception("Method: updateDepartment | Reason: " + e.getMessage(), e);
		}
	}

	public List<DepartmentResponse> listDepartments() throws Exception {
		try {
			return departmentRepository.findAll().stream().map(department -> {
				try {
					return DepartmentETL.convertDepartmentToDepartmentResponse(department, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}).toList();
		} catch (Exception e) {
			throw new Exception("Method: listDepartments | Reason: " + e.getMessage(), e);
		}
	}

	public DepartmentResponse findDepartment(Long id) throws Exception {
		try {
			Optional<Department> departmentOptional = departmentRepository.findById(id);
			if (departmentOptional.isPresent()) {
				return DepartmentETL.convertDepartmentToDepartmentResponse(departmentOptional.get(), true);
			}
			throw new Exception("Department not found");
		} catch (Exception e) {
			throw new Exception("Method: findDepartment | Reason: " + e.getMessage(), e);
		}
	}
}
