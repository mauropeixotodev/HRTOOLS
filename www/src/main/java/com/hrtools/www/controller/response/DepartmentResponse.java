package com.hrtools.www.controller.response;

import java.util.Date;
import java.util.List;
import com.hrtools.www.model.Employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentResponse {

	private Long id;

	private String name;

	private String description;

	private String company;

	private String owner;

	private List<EmployeeResponse> employees;

	private List<PositionResponse> positions;

	private Double budget;

	private String email;

	private String phoneNumber;

	private Date dateOfestablishment;

}
