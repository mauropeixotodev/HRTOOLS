package com.hrtools.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrtools.www.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
