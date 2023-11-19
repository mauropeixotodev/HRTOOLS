package com.hrtools.www.etl;

import java.util.ArrayList;
import java.util.List;

import com.hrtools.www.controller.request.CompanyRequest;
import com.hrtools.www.controller.response.CompanyResponse;
import com.hrtools.www.model.Company;
import com.hrtools.www.model.Department;

public class CompanyETL {

	public static Company convertCompanyRequestToCompany(CompanyRequest companyRequest, Company company)
			throws Exception {

		try {

			if (companyRequest.getName() != null)
				company.setName(companyRequest.getName());
			if (companyRequest.getLegalStructure() != null)
				company.setLegalStructure(companyRequest.getLegalStructure());
			if (companyRequest.getIndustry() != null)
				company.setIndustry(companyRequest.getIndustry());
			if (companyRequest.getHeadquartersAddress() != null)
				company.setHeadquartersAddress(companyRequest.getHeadquartersAddress());
			if (companyRequest.getRegistrationNumber() != null)
				company.setRegistrationNumber(companyRequest.getRegistrationNumber());
			if (companyRequest.getFoundingDate() != null)
				company.setFoundingDate(companyRequest.getFoundingDate());
			if (companyRequest.getCeo() != null)
				company.setCeo(companyRequest.getCeo());
			if (companyRequest.getAnnualRevenue() != null)
				company.setAnnualRevenue(companyRequest.getAnnualRevenue());
			if (companyRequest.getMissionStatement() != null)
				company.setMissionStatement(companyRequest.getMissionStatement());
			if (companyRequest.getVisionStatement() != null)
				company.setVisionStatement(companyRequest.getVisionStatement());
			return company;

		} catch (Exception e) {
			throw new Exception("Method: convertCompanyRequestToCompany | Reason: " + e.getMessage(), e);
		}

	}

	public static CompanyResponse convertCompanyToCompanyResponse(Company company, boolean cascade, List<Department> department) throws Exception {

		try {

			CompanyResponse companyResponse = CompanyResponse.builder().id(company.getId()).name(company.getName())
					.legalStructure(company.getLegalStructure()).industry(company.getIndustry())
					.headquartersAddress(company.getHeadquartersAddress())
					.registrationNumber(company.getRegistrationNumber()).foundingDate(company.getFoundingDate())
					.ceo(company.getCeo()).annualRevenue(company.getAnnualRevenue())
					.missionStatement(company.getMissionStatement()).VisionStatement(company.getVisionStatement())
					.build();
			if (cascade) {
				if (department != null) {
					companyResponse.setDepartment(department.stream().map(e -> {
						try {
							return DepartmentETL.convertDepartmentToDepartmentResponse(e, false);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return null;
					}).toList());
				}
				if (company.getEmployees() != null && cascade) {
					companyResponse.setEmployees(new ArrayList<>());
				}

			} else {
				companyResponse.setEmployees(new ArrayList<>());
				companyResponse.setDepartment(new ArrayList<>());

			}

			return companyResponse;
		} catch (Exception e) {
			throw new Exception("Method: convertCompanyToCompanyResponse | Reason: " + e.getMessage(), e);
		}

	}

}
