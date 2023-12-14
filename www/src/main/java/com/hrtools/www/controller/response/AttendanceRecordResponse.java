package com.hrtools.www.controller.response;

import java.util.Date;

import com.hrtools.www.model.Employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceRecordResponse {

	private Long id;

	private Date dayDate;

	private Date clockIn;

	private Date clockOut;

	private int status;

	private String employee;

}
