package com.hrtools.www.etl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hrtools.www.controller.request.EmployeeRequest;
import com.hrtools.www.controller.response.EmployeeResponse;
import com.hrtools.www.model.Employee;
import com.hrtools.www.model.Company;
import com.hrtools.www.model.Position;
import com.hrtools.www.model.Department;

public class EmployeeETL {

    public static Employee convertEmployeeRequestToEmployee(EmployeeRequest employeeRequest, Employee employee, 
                                                           Company company, Employee manager, Position position, Department department) throws Exception {
        try {
            if (employeeRequest.getFullname() != null)
                employee.setFullname(employeeRequest.getFullname());
            if (employeeRequest.getEmailAddress() != null)
                employee.setEmailAddress(employeeRequest.getEmailAddress());
            if (employeeRequest.getPassword() != null)
            	System.out.println("AQUIIIIIIIIII "+new BCryptPasswordEncoder().encode(employeeRequest.getPassword()));
                employee.setPassword(new BCryptPasswordEncoder().encode(employeeRequest.getPassword()));
            if (company != null)
                employee.setCompany(company);
            if (manager != null)
                employee.setManager(manager);
            if (position != null)
                employee.setPosition(position);
            if (department != null)
                employee.setDepartment(department);
            if (employeeRequest.getDateOfJoining() != null)
                employee.setDateOfJoining(employeeRequest.getDateOfJoining());
            if (employeeRequest.getDateOfTermination() != null)
                employee.setDateOfTermination(employeeRequest.getDateOfTermination());
            if (employeeRequest.getPhoneNumeber() != null)
                employee.setPhoneNumeber(employeeRequest.getPhoneNumeber());
            if (employeeRequest.getEmployeeType() != null)
                employee.setEmployeeType(employeeRequest.getEmployeeType());
            if (employeeRequest.isActivityStatus())
                employee.setActivityStatus(employeeRequest.isActivityStatus());
            if (employeeRequest.getRoles() != null)
             //   employee.setRoles(employeeRequest.getRoles());
            if (employeeRequest.getUsername() != null)
            	System.out.println("AQUIIIIIIIIIIIIII      "+employeeRequest.getUsername());
                employee.setUsername(employeeRequest.getUsername());
                
                
                
                
                System.out.println(employee.getUsername());
                
            return employee;
        } catch (Exception e) {
            throw new Exception("Method: convertEmployeeRequestToEmployee | Reason: " + e.getMessage(), e);
        }
    }
    
    public static EmployeeResponse convertEmployeeToEmployeeResponse(Employee employee, boolean cascade) throws Exception {
        try {
            EmployeeResponse employeeResponse = EmployeeResponse.builder()
                    .id(employee.getId())
                    .fullname(employee.getFullname())
                    .emailAddress(employee.getEmailAddress())
                    .password(employee.getPassword()) // Note: Generally, it's not recommended to include passwords in responses
                    .dateOfJoining(employee.getDateOfJoining())
                    .dateOfTermination(employee.getDateOfTermination())
                    .phoneNumeber(employee.getPhoneNumeber())
                    .employeeType(employee.getEmployeeType())
                    .activityStatus(employee.isActivityStatus())
                    .username(employee.getUsername())
                    .build();
            
            if(employee.getCompany()!= null) employeeResponse.setCompany(employee.getCompany().getName());
            if(employee.getDepartment()!= null) employeeResponse.setDepartment(employee.getDepartment().getName());
            if(employee.getPosition() != null) employeeResponse.setPosition(employee.getPosition().getTitle());

            if (cascade) {
                if (employee.getManager() != null) {
                	employeeResponse.setManager(employee.getManager().getFullname());;
                }
                if (employee.getDirectReports() != null) {
                	employeeResponse.setDirectReports(employee.getDirectReports().stream().map(e -> {
						try {
							return convertEmployeeToEmployeeResponse(e, false);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return employeeResponse;
					}).toList());
                }
            }

            return employeeResponse;
        } catch (Exception e) {
            throw new Exception("Method: convertEmployeeToEmployeeResponse | Reason: " + e.getMessage(), e);
        }
    }

}
