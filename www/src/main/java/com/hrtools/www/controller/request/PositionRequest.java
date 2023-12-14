package com.hrtools.www.controller.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionRequest {

	
	private String title;
	
	private String description;
	
	private Long department;
	
	private Double salaryRange;
	
	private String qualifications;
	
	private String status;
	
	private String employmentType;

}
