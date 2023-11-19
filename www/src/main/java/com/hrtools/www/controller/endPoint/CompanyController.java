package com.hrtools.www.controller.endPoint;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hrtools.www.controller.request.CompanyRequest;
import com.hrtools.www.controller.response.CompanyResponse;
import com.hrtools.www.service.CompanyService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@GetMapping("/company")
	public List<CompanyResponse> listCompany() throws Exception {

		return companyService.listCompany();
	}

	@PostMapping("/company")
	public ResponseEntity<CompanyResponse> createCompany(@RequestBody @Validated CompanyRequest companyRequest,
			UriComponentsBuilder uriBuilder) throws Exception {
		CompanyResponse company = companyService.createCompany(companyRequest);
		URI uri = uriBuilder.path("/company/{id}").buildAndExpand(company.getId()).toUri();
		return ResponseEntity.created(uri).body(company);
	}

	@PutMapping("/company/{id}")
	public ResponseEntity<CompanyResponse> updateCompany(@RequestBody @Validated CompanyRequest companyRequest, @PathVariable Long id) throws Exception {
		try {
			return ResponseEntity.ok(companyService.updateCompany(companyRequest, id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/company/{id}")
	public ResponseEntity<CompanyResponse> findCompany(@PathVariable Long id) throws Exception {
		try {
			return ResponseEntity.ok(companyService.findCompany(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
