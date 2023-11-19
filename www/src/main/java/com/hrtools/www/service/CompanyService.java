package com.hrtools.www.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrtools.www.controller.request.CompanyRequest;
import com.hrtools.www.controller.response.CompanyResponse;
import com.hrtools.www.etl.CompanyETL;
import com.hrtools.www.model.Company;
import com.hrtools.www.repository.CompanyRepository;

import jakarta.transaction.Transactional;

@Service
public class CompanyService {
	@Autowired
	CompanyRepository companyRepository;

	public CompanyResponse createCompany(CompanyRequest companyRequest) throws Exception {
		try {

			return CompanyETL.convertCompanyToCompanyResponse(
					companyRepository
							.save(CompanyETL.convertCompanyRequestToCompany(companyRequest, Company.builder().build())),
					true, null);

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	@Transactional
	public CompanyResponse updateCompany(CompanyRequest companyRequest, Long id) throws Exception {
		try {

			Optional<Company> companyOpitional = companyRepository.findById(id);
			if (companyOpitional.isPresent()) {
				Company company = companyOpitional.get();
				CompanyETL.convertCompanyRequestToCompany(companyRequest, company);
				return CompanyETL.convertCompanyToCompanyResponse(company, true, company.getDepartment());
			}

			throw new Exception("Compnay não encontrada");

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	public List<CompanyResponse> listCompany() throws Exception {
		try {

			return companyRepository.findAll().stream().map(company -> {
				try {
					return CompanyETL.convertCompanyToCompanyResponse(company, true, company.getDepartment());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}).toList();

		} catch (Exception e) {
			throw new Exception("Metodo: listCompany | Motivo: " + e.getMessage());
		}

	}

	public CompanyResponse findCompany(Long id) throws Exception {
		try {
			Optional<Company> companyOptional = companyRepository.findById(id);

			if (companyOptional.isPresent())
				return CompanyETL.convertCompanyToCompanyResponse(companyOptional.get(), true, companyOptional.get().getDepartment());

			throw new Exception("Company não encontrada");

		} catch (Exception e) {
			throw new Exception("Metodo: listCompany | Motivo: " + e.getMessage());
		}

	}

}
