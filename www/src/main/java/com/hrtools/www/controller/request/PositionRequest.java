package com.hrtools.www.controller.request;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionRequest {

	@Nonnull
	private String title;
	@Nonnull
	private String description;
	@Nonnull
	private Long department;
	@Nonnull
	private Double salaryRange;
	@Nonnull
	private String qualifications;
	@Nonnull
	private String status;
	@Nonnull
	private String employmentType;

}
