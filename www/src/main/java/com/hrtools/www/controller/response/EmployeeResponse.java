package com.hrtools.www.controller.response;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponse {

	private Long id;

	private String fullname;

	private String emailAddress;

	private String password;

	private String company;

	private String manager;
	
	private String username;

	private List<EmployeeResponse> directReports;

	private String position;

	private String department;

	private Date dateOfJoining;

	private Date dateOfTermination;

	private String phoneNumeber;

	private String employeeType;

	private boolean activityStatus;

	private List<NotificationResponse> notification;

	private String roles;

}
