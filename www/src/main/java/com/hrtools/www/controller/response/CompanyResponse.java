package com.hrtools.www.controller.response;

import java.util.List;

import com.hrtools.www.model.Department;
import com.hrtools.www.model.Employee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyResponse {

	private Long id;

	private String name;

	private String legalStructure;

	private String industry;

	private String headquartersAddress;

	private String registrationNumber;

	private String foundingDate;

	private String ceo;

	private List<DepartmentResponse> department;

	private List<EmployeeResponse> employees;

	private Double annualRevenue;

	private String missionStatement;

	private String VisionStatement;

}
