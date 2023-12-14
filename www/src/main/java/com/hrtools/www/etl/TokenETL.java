package com.hrtools.www.etl;

import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Optional;

import com.hrtools.www.model.Employee;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import com.hrtools.www.repository.EmployeeRepository;
import com.hrtools.www.service.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenETL extends OncePerRequestFilter {

	private TokenService tokenService;
	private EmployeeRepository repository;

	public TokenETL(TokenService tokenService, EmployeeRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = fetchToken(request);
		boolean valid = tokenService.tokenIsValid(token);
		if (valid) {
			authCliente(token);
		}

		filterChain.doFilter(request, response);
	}

	private void authCliente(String token) {
		Long idEmployee = tokenService.getIdEmployee(token);
		Optional<Employee> optionalEmployee = repository.findById(idEmployee);
		if (optionalEmployee.isPresent()) {
			Employee useAutehticado = optionalEmployee.get();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					useAutehticado, null, useAutehticado.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private String fetchToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}

}