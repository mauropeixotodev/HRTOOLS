package com.hrtools.www.controller.request;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class NotificationRequest {
	
	
	private String status;
	
	private String title;
	
	private String description;
	
	private List<Long> employees;

}
