package com.hrtools.www.controller.response;

import java.util.List;

import com.hrtools.www.model.Employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponse {
	
	private Long id;

	private String status;

	private String title;

	private String description;

	private List<EmployeeResponse> employees;
}
