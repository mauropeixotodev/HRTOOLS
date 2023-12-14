package com.hrtools.www.controller.response;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {
	
	private String type;

	private String token;

	private Date dateExpiration;

}
