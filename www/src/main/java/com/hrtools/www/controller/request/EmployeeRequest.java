package com.hrtools.www.controller.request;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeRequest {
	
	private String fullname;
	
	private String emailAddress;
	
	private String password;
	
	private Long company;
	
	private Long manager;
	
	private Long position;
	
	private Long department;
	
	private Date dateOfJoining;
	
	private Date dateOfTermination;
	
	private String phoneNumeber;
	
	private String employeeType;
	
	private boolean activityStatus;
	
	private String username;
	
	private String roles;
	
	public UsernamePasswordAuthenticationToken getAuth() {
		return new UsernamePasswordAuthenticationToken(this.username, this.password);
	}

}
