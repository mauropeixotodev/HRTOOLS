package com.hrtools.www.etl;

import com.hrtools.www.controller.request.NotificationRequest;
import com.hrtools.www.controller.response.NotificationResponse;
import com.hrtools.www.model.Notification;
import com.hrtools.www.model.Employee;
import java.util.List;

public class NotificationETL {

    public static Notification convertNotificationRequestToNotification(NotificationRequest request, List<Employee> employees) {
        Notification.NotificationBuilder builder = Notification.builder();

        if (request != null) {
            if (request.getStatus() != null) {
                builder.status(request.getStatus());
            }
            if (request.getTitle() != null) {
                builder.title(request.getTitle());
            }
            if (request.getDescription() != null) {
                builder.description(request.getDescription());
            }
        }

        if (employees != null && !employees.isEmpty()) {
            builder.employees(employees);
        }

        return builder.build();
    }
    
    
    public static NotificationResponse convertNotificationToNotificationResponse(Notification notification) throws Exception {
        try {
            return NotificationResponse.builder()
                    .id(notification.getId())
                    .status(notification.getStatus())
                    .title(notification.getTitle())
                    .description(notification.getDescription())
                    .employees(notification.getEmployees().stream()
                        .map(employee -> {
							try {
								return EmployeeETL.convertEmployeeToEmployeeResponse(employee, false);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						}).toList())
                    .build();
        } catch (Exception e) {
            throw new Exception("Method: convertNotificationToNotificationResponse | Reason: " + e.getMessage(), e);
        }
    }

   
}
