package com.hrtools.www.controller.request;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.annotation.Nonnull;

@Data
@NoArgsConstructor
public class NotificationRequest {
	
	@Nonnull
	private String status;
	@Nonnull
	private String title;
	@Nonnull
	private String description;
	@Nonnull
	private List<Long> employees;

}
