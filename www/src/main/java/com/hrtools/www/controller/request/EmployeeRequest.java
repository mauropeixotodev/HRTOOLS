package com.hrtools.www.controller.request;

import java.util.Date;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeRequest {
	@Nonnull
	private String fullname;
	@Nonnull
	private String emailAddress;
	@Nonnull
	private String password;
	@Nonnull
	private Long company;
	@Nonnull
	private Long manager;
	@Nonnull
	private Long position;
	@Nonnull
	private Long department;
	@Nonnull
	private Date dateOfJoining;
	@Nonnull
	private Date dateOfTermination;
	@Nonnull
	private String phoneNumeber;
	@Nonnull
	private String employeeType;
	@Nonnull
	private String activityStatus;
	@Nonnull
	private String roles;

}
