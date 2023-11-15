package com.hrtools.www.controller.request;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttendanceRecordRequest {

	@Nonnull
	private String Status;
	@Nonnull
	private Long employee;

}
