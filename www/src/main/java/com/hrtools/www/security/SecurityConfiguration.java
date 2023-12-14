package com.hrtools.www.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hrtools.www.etl.TokenETL;
import com.hrtools.www.repository.EmployeeRepository;
import com.hrtools.www.service.TokenService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
	
	
	
		@Autowired
		EmployeeRepository employeerepository;
	
		@Autowired
		TokenService tokenService;
		
		@Bean
		public RoleHierarchy roleHierarchy() {
		    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		    String hierarchy = "ROLE_ADMIN > ROLE_STAFF > ROLE_USER";
		    roleHierarchy.setHierarchy(hierarchy);
		    return roleHierarchy;
		}
		
		@Bean
		public DefaultWebSecurityExpressionHandler customWebSecurityExpressionHandler() {
		    DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
		    expressionHandler.setRoleHierarchy(roleHierarchy());
		    return expressionHandler;
		}
		
		
	  @Bean
	    PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }

	  @Bean
	    public AuthenticationManager authenticationManager(
	            AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	   @Bean
	    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		   http
	        .csrf().disable()
	        .exceptionHandling()
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authorizeRequests()
	        .expressionHandler(customWebSecurityExpressionHandler()) // Adicione esta linha
	        .antMatchers("/position/**").hasRole("STAFF")
	        .antMatchers("/company/**").hasRole("STAFF")
	        .antMatchers("/departament/**").hasRole("STAFF")
	        .antMatchers("/attendanceRecord/**").hasRole("USER")
	        .antMatchers("/h2-console/**").permitAll()
	        .antMatchers("/auth").permitAll()
	        .anyRequest().authenticated();
	        http.addFilterBefore(new TokenETL(tokenService, employeerepository), UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }
}