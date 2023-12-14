package com.hrtools.www.controller.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PositionResponse {

	private Long id;

	private String title;

	private String description;

	private String department;

	private List<EmployeeResponse> reportsTo;

	private Double salaryRange;

	private String qualifications;

	private String status;

	private String employmentType;

}
