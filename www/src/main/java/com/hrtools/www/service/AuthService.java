package com.hrtools.www.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.hrtools.www.model.Employee;
import com.hrtools.www.model.Privilege;
import com.hrtools.www.model.Role;
import com.hrtools.www.repository.EmployeeRepository;
import com.hrtools.www.repository.RoleRepository;


@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private EmployeeRepository repository;
 

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = repository.findByUsername(username);
        if (employee == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        System.out.println(employee.getAuthorities());
        
        return employee;
    }
    
   
}