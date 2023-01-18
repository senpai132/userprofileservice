package com.profilemanagement.repositories;

import com.profilemanagement.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByRecipient(String recipient);
    void deleteAllByRecipient(String recipient);
}
