package com.hrtools.www.controller.request;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyRequest {
	
	@Nonnull
	private String name;
	@Nonnull
	private String legalStructure;
	@Nonnull
	private String industry;
	@Nonnull
	private String headquartersAddress;
	@Nonnull
	private String registrationNumber;
	@Nonnull
	private String foundingDate;
	@Nonnull
	private String ceo;
	@Nonnull
	private Double annualRevenue;
	@Nonnull
	private String missionStatement;
	@Nonnull
	private String VisionStatement;
}
