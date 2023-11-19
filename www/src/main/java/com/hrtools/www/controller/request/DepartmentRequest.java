package com.hrtools.www.controller.request;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentRequest {

	private String name;

	private String description;

	private Long company;

	private Long owner;

	private Double budget;

	private String email;

	private String phoneNumber;

	private Date dateOfestablishment;

}
