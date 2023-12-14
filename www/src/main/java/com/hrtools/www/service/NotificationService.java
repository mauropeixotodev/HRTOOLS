package com.hrtools.www.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrtools.www.controller.request.NotificationRequest;
import com.hrtools.www.controller.response.NotificationResponse;
import com.hrtools.www.etl.NotificationETL;
import com.hrtools.www.model.Notification;
import com.hrtools.www.model.Employee;
import com.hrtools.www.repository.EmployeeRepository;
import com.hrtools.www.repository.NotificationRepository;
import javax.transaction.Transactional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public NotificationResponse createNotification(NotificationRequest notificationRequest) throws Exception {
        try {
            List<Employee> employees = employeeRepository.findAllById(notificationRequest.getEmployees());
            return NotificationETL.convertNotificationToNotificationResponse(
                    notificationRepository.save(
                            NotificationETL.convertNotificationRequestToNotification(notificationRequest, employees)));
        } catch (Exception e) {
            throw new Exception("Method: createNotification | Reason: " + e.getMessage(), e);
        }
    }

    @Transactional
    public NotificationResponse updateNotification(NotificationRequest notificationRequest, Long id) throws Exception {
        try {
            Optional<Notification> notificationOptional = notificationRepository.findById(id);
            if (notificationOptional.isPresent()) {
                Notification notification = notificationOptional.get();
                List<Employee> employees = employeeRepository.findAllById(notificationRequest.getEmployees());
                NotificationETL.convertNotificationRequestToNotification(notificationRequest, employees);
                return NotificationETL.convertNotificationToNotificationResponse(notification);
            }
            throw new Exception("Notification not found");
        } catch (Exception e) {
            throw new Exception("Method: updateNotification | Reason: " + e.getMessage(), e);
        }
    }

    public List<NotificationResponse> listNotifications() throws Exception {
        try {
            return notificationRepository.findAll().stream().map(notification -> {
                try {
                    return NotificationETL.convertNotificationToNotificationResponse(notification);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }).toList();
        } catch (Exception e) {
            throw new Exception("Method: listNotifications | Reason: " + e.getMessage(), e);
        }
    }

    public NotificationResponse findNotification(Long id) throws Exception {
        try {
            Optional<Notification> notificationOptional = notificationRepository.findById(id);
            if (notificationOptional.isPresent()) {
                return NotificationETL.convertNotificationToNotificationResponse(notificationOptional.get());
            }
            throw new Exception("Notification not found");
        } catch (Exception e) {
            throw new Exception("Method: findNotification | Reason: " + e.getMessage(), e);
        }
    }
}
