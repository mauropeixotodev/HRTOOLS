package com.hrtools.www.etl;

import java.util.ArrayList;
import com.hrtools.www.controller.request.DepartmentRequest;
import com.hrtools.www.controller.response.DepartmentResponse;
import com.hrtools.www.model.Company;
import com.hrtools.www.model.Department;
import com.hrtools.www.model.Employee;

public class DepartmentETL {

	public static Department convertDepartmentRequestToDepartment(DepartmentRequest departmentRequest,
			Department department, Company company, Employee owner) throws Exception {
		try {
			if (departmentRequest.getName() != null)
				department.setName(departmentRequest.getName());
			if (departmentRequest.getDescription() != null)
				department.setDescription(departmentRequest.getDescription());
			if (company != null)
				department.setCompany(company);
			if (owner != null)
				department.setOwner(owner);
			if (departmentRequest.getBudget() != null)
				department.setBudget(departmentRequest.getBudget());
			if (departmentRequest.getEmail() != null)
				department.setEmail(departmentRequest.getEmail());
			if (departmentRequest.getPhoneNumber() != null)
				department.setPhoneNumber(departmentRequest.getPhoneNumber());
			if (departmentRequest.getDateOfestablishment() != null)
				department.setDateOfestablishment(departmentRequest.getDateOfestablishment());
			return department;
		} catch (Exception e) {
			throw new Exception("Method: convertDepartmentRequestToDepartment | Reason: " + e.getMessage(), e);
		}
	}

	public static DepartmentResponse convertDepartmentToDepartmentResponse(Department department, boolean cascade)
			throws Exception {
		try {
			DepartmentResponse departmentResponse = DepartmentResponse.builder().id(department.getId())
					.name(department.getName()).description(department.getDescription())
					.company(CompanyETL.convertCompanyToCompanyResponse(department.getCompany(), false, null))
					.owner(null).budget(department.getBudget()).email(department.getEmail())
					.phoneNumber(department.getPhoneNumber()).dateOfestablishment(department.getDateOfestablishment())
					.build();

			if (cascade) {
				if (department.getEmployees() != null) {
					// Convert each Employee to EmployeeResponse and add to the list
					departmentResponse.setEmployees(new ArrayList<>());
				} else {
					departmentResponse.setEmployees(new ArrayList<>());
				}
				if (department.getPositions() != null) {
					// Convert each Position to PositionResponse and add to the list
					departmentResponse.setPositions(department.getPositions().stream().map(e -> {
						try {
							return PositionETL.convertPositionToPositionResponse(e, false);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return null;
					}).toList());
				} else {
					departmentResponse.setPositions(new ArrayList<>());
				}
			} else {
				departmentResponse.setPositions(new ArrayList<>());
				departmentResponse.setEmployees(new ArrayList<>());
			}

			return departmentResponse;
		} catch (Exception e) {
			throw new Exception("Method: convertDepartmentToDepartmentResponse | Reason: " + e.getMessage(), e);
		}
	}

}
