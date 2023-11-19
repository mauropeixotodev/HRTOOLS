package com.hrtools.www.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrtools.www.controller.request.EmployeeRequest;
import com.hrtools.www.controller.response.EmployeeResponse;
import com.hrtools.www.etl.EmployeeETL;
import com.hrtools.www.model.Employee;
import com.hrtools.www.model.Company;
import com.hrtools.www.model.Position;
import com.hrtools.www.model.Department;
import com.hrtools.www.repository.CompanyRepository;
import com.hrtools.www.repository.DepartmentRepository;
import com.hrtools.www.repository.EmployeeRepository;
import com.hrtools.www.repository.PositionRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private PositionRepository positionRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;

    
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) throws Exception {
        try {
            // Logic to get Company, Manager, Position, and Department should be added here
        	Company company = null;
			Optional<Company> companyOpitional = companyRepository.findById(employeeRequest.getCompany());
			if(companyOpitional.isPresent()) {
				company = companyOpitional.get();
			}
			Employee manager = null;
			Optional<Employee> managerOpitional = employeeRepository.findById(employeeRequest.getManager());
			if(managerOpitional.isPresent()) {
				manager = managerOpitional.get();
			}
			Position position = null;
			Optional<Position> positionOpitional = positionRepository.findById(employeeRequest.getPosition());
			if(positionOpitional.isPresent()) {
				position = positionOpitional.get();
			}
			Department department = null;
			Optional<Department> departmentOpitional = departmentRepository.findById(employeeRequest.getDepartment());
			if(departmentOpitional.isPresent()) {
				department = departmentOpitional.get();
			}
        	
        	
        	
        	
        	
        	
            Employee employee = EmployeeETL.convertEmployeeRequestToEmployee(employeeRequest, new Employee(), company, manager, position, department);
            return EmployeeETL.convertEmployeeToEmployeeResponse(employeeRepository.save(employee), true);
        } catch (Exception e) {
            throw new Exception("Method: createEmployee | Reason: " + e.getMessage(), e);
        }
    }

    @Transactional
    public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest, Long id) throws Exception {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                // Logic to get Company, Manager, Position, and Department should be added here
            	Company company = null;
    			Optional<Company> companyOpitional = companyRepository.findById(employeeRequest.getCompany());
    			if(companyOpitional.isPresent()) {
    				company = companyOpitional.get();
    			}
    			Employee manager = null;
    			Optional<Employee> managerOpitional = employeeRepository.findById(employeeRequest.getManager());
    			if(managerOpitional.isPresent()) {
    				manager = managerOpitional.get();
    			}
    			Position position = null;
    			Optional<Position> positionOpitional = positionRepository.findById(employeeRequest.getPosition());
    			if(positionOpitional.isPresent()) {
    				position = positionOpitional.get();
    			}
    			Department department = null;
    			Optional<Department> departmentOpitional = departmentRepository.findById(employeeRequest.getDepartment());
    			if(departmentOpitional.isPresent()) {
    				department = departmentOpitional.get();
    			}

                EmployeeETL.convertEmployeeRequestToEmployee(employeeRequest, employee, company, manager, position, department);
                return EmployeeETL.convertEmployeeToEmployeeResponse(employee, true);
            }
            throw new Exception("Employee not found");
        } catch (Exception e) {
            throw new Exception("Method: updateEmployee | Reason: " + e.getMessage(), e);
        }
    }

    public List<EmployeeResponse> listEmployees() throws Exception {
        try {
            return employeeRepository.findAll().stream().map(employee -> {
                try {
                    return EmployeeETL.convertEmployeeToEmployeeResponse(employee, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }).toList();
        } catch (Exception e) {
            throw new Exception("Method: listEmployees | Reason: " + e.getMessage());
        }
    }

    public EmployeeResponse findEmployee(Long id) throws Exception {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);
            if (employeeOptional.isPresent()) {
                return EmployeeETL.convertEmployeeToEmployeeResponse(employeeOptional.get(), true);
            }
            throw new Exception("Employee not found");
        } catch (Exception e) {
            throw new Exception("Method: findEmployee | Reason: " + e.getMessage(), e);
        }
    }
}
