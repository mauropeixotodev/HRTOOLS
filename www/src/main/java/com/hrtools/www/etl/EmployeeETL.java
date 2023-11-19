package com.hrtools.www.etl;

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
                employee.setPassword(employeeRequest.getPassword());
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
            if (employeeRequest.getActivityStatus() != null)
                employee.setActivityStatus(employeeRequest.getActivityStatus());
            if (employeeRequest.getRoles() != null)
                employee.setRoles(employeeRequest.getRoles());
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
                    .company(CompanyETL.convertCompanyToCompanyResponse(employee.getCompany(), false, null)) // Convert Company to CompanyResponse
                    .position(PositionETL.convertPositionToPositionResponse(employee.getPosition(), false)) // Convert Position to PositionResponse
                    .department(DepartmentETL.convertDepartmentToDepartmentResponse(employee.getDepartment(), false)) // Convert Department to DepartmentResponse
                    .dateOfJoining(employee.getDateOfJoining())
                    .dateOfTermination(employee.getDateOfTermination())
                    .phoneNumeber(employee.getPhoneNumeber())
                    .employeeType(employee.getEmployeeType())
                    .activityStatus(employee.getActivityStatus())
                    .roles(employee.getRoles()).build();

            if (cascade) {
                if (employee.getManager() != null) {
                	employeeResponse.setManager(convertEmployeeToEmployeeResponse(employee.getManager(), false));;
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
