package com.hrtools.www.controller.endPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrtools.www.controller.request.EmployeeRequest;
import com.hrtools.www.controller.response.TokenResponse;
import com.hrtools.www.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenResponse> autenticar(@RequestBody @Validated EmployeeRequest employeeRequest)
			throws Exception {
		UsernamePasswordAuthenticationToken dadosLogin = employeeRequest.getAuth();

		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			System.out.println("chegou aqui");

			return ResponseEntity.ok(tokenService.genereteToken(authentication));
		} catch (AuthenticationException e) {
			throw new Exception("E-mail ou senha inválidos");
		}
	}

}