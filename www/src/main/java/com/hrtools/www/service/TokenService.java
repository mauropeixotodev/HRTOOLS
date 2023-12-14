package com.hrtools.www.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.hrtools.www.controller.response.TokenResponse;
import com.hrtools.www.model.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public TokenResponse genereteToken(Authentication authentication) {
		
		Employee loged = (Employee) authentication.getPrincipal();
		Date today = new Date();
		Date dateExpiration = new Date(today.getTime() + Long.parseLong(expiration));
		   
		String token = Jwts.builder()
		.setIssuer("HRTOOLS")
		.setSubject(loged.getId().toString())
		.setIssuedAt(today)
		.setExpiration(dateExpiration)
		.signWith(SignatureAlgorithm.HS256, secret)
		.compact();
		
		
		return TokenResponse.builder()
				.dateExpiration(dateExpiration)
				.token(token)
				.type("Bearer")
				.build();
	}
	
	public boolean tokenIsValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdEmployee(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}