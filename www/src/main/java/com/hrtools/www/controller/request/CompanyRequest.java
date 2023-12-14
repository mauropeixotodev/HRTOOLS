package com.hrtools.www.controller.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyRequest {
	
	
	private String name;
	
	private String legalStructure;
	
	private String industry;
	
	private String headquartersAddress;
	
	private String registrationNumber;
	
	private String foundingDate;
	
	private String ceo;
	
	private Double annualRevenue;
	
	private String missionStatement;
	
	private String VisionStatement;
}
