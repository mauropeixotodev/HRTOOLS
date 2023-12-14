package com.hrtools.www.controller.endPoint;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.hrtools.www.controller.request.NotificationRequest;
import com.hrtools.www.controller.response.NotificationResponse;
import com.hrtools.www.service.NotificationService;
import javax.persistence.EntityNotFoundException;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification")
    public List<NotificationResponse> listNotifications() throws Exception {
        return notificationService.listNotifications();
    }

    @PostMapping("/notification")
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody @Validated NotificationRequest notificationRequest,
                                                                   UriComponentsBuilder uriBuilder) throws Exception {
        NotificationResponse notification = notificationService.createNotification(notificationRequest);
        URI uri = uriBuilder.path("/notification/{id}").buildAndExpand(notification.getId()).toUri();
        return ResponseEntity.created(uri).body(notification);
    }

    @PutMapping("/notification/{id}")
    public ResponseEntity<NotificationResponse> updateNotification(@RequestBody @Validated NotificationRequest notificationRequest,
                                                                   @PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(notificationService.updateNotification(notificationRequest, id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/notification/{id}")
    public ResponseEntity<NotificationResponse> findNotification(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(notificationService.findNotification(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
