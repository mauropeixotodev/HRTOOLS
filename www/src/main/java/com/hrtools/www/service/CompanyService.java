package com.hrtools.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrtools.www.controller.request.CompanyRequest;
import com.hrtools.www.controller.response.CompanyResponse;
import com.hrtools.www.model.Company;
import com.hrtools.www.repository.CompanyRepository;

@Service
public class CompanyService {
	@Autowired
	CompanyRepository companyRepository;

	public CompanyResponse createCompany(CompanyRequest companyRequest) throws Exception {
		try {
			
			Company company = Company.builder().name(companyRequest.getName())
					.legalStructure(companyRequest.getLegalStructure()).industry(companyRequest.getIndustry())
					.headquartersAddress(companyRequest.getHeadquartersAddress())
					.registrationNumber(companyRequest.getRegistrationNumber())
					.foundingDate(companyRequest.getFoundingDate()).ceo(companyRequest.getCeo())
					.annualRevenue(companyRequest.getAnnualRevenue())
					.missionStatement(companyRequest.getMissionStatement())
					.VisionStatement(companyRequest.getVisionStatement()).build();
			
			company = companyRepository.save(company);
			
			return CompanyResponse.builder().id(company.getId()).name(company.getName())
					.legalStructure(company.getLegalStructure()).industry(company.getIndustry())
					.headquartersAddress(company.getHeadquartersAddress())
					.registrationNumber(company.getRegistrationNumber())
					.foundingDate(company.getFoundingDate()).ceo(company.getCeo())
					.annualRevenue(company.getAnnualRevenue())
					.missionStatement(company.getMissionStatement())
					.VisionStatement(company.getVisionStatement()).build();
			

		} catch (Exception e) {
			throw new Exception("Metodo: createCompany | Motivo: "+e.getMessage());
		}

	}

}
