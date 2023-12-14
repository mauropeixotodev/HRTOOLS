package com.hrtools.www.etl;

import java.util.ArrayList;

import com.hrtools.www.controller.request.PositionRequest;
import com.hrtools.www.controller.response.PositionResponse;
import com.hrtools.www.model.Position;
import com.hrtools.www.model.Department;

public class PositionETL {

    public static Position convertPositionRequestToPosition(PositionRequest positionRequest, Position position, Department department) throws Exception {
        try {
            if (positionRequest.getTitle() != null)
                position.setTitle(positionRequest.getTitle());
            if (positionRequest.getDescription() != null)
                position.setDescription(positionRequest.getDescription());
            if (department != null)
                position.setDepartment(department); // Assuming department object is provided
            if (positionRequest.getSalaryRange() != null)
                position.setSalaryRange(positionRequest.getSalaryRange());
            if (positionRequest.getQualifications() != null)
                position.setQualifications(positionRequest.getQualifications());
            if (positionRequest.getStatus() != null)
                position.setStatus(positionRequest.getStatus());
            if (positionRequest.getEmploymentType() != null)
                position.setEmploymentType(positionRequest.getEmploymentType());
            // No need to set reportsTo here as it is managed elsewhere
            return position;
        } catch (Exception e) {
            throw new Exception("Method: convertPositionRequestToPosition | Reason: " + e.getMessage(), e);
        }
    }
    
    public static PositionResponse convertPositionToPositionResponse(Position position, boolean cascade) throws Exception {
        try {
            PositionResponse positionResponse = PositionResponse.builder()
                    .id(position.getId())
                    .title(position.getTitle())
                    .description(position.getDescription())
                    .department(position.getDepartment().getName()) // Convert Department to DepartmentResponse
                    .salaryRange(position.getSalaryRange())
                    .qualifications(position.getQualifications())
                    .status(position.getStatus())
                    .employmentType(position.getEmploymentType())
                    .build();

            if (cascade) {
                if (position.getReportsTo() != null) {
                    
                    positionResponse.setReportsTo(position.getReportsTo().stream().map(e -> {
						try {
							return EmployeeETL.convertEmployeeToEmployeeResponse(e, false);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return null;
					}).toList());
                }else {
                	positionResponse.setReportsTo(new ArrayList<>());
                }
                // Additional conversion logic can be added here if needed
            }

            return positionResponse;
        } catch (Exception e) {
            throw new Exception("Method: convertPositionToPositionResponse | Reason: " + e.getMessage(), e);
        }
    }

}

