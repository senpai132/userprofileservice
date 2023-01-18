package com.profilemanagement.services;

import com.profilemanagement.model.Notification;
import com.profilemanagement.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.profilemanagement.model.Followship;
import com.profilemanagement.repositories.FollowshipRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class NotificationsService {
    @Autowired
    FollowshipRepository followshipRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    SimpMessagingTemplate template;

    public Followship getNotificationSettings(String initiator, String target) {
        return followshipRepository.findByInitiatorAndTarget(initiator, target);
    }
    public String editNotificationsOnMessage(String username_initiator, String username_target, int notify) {
        Followship follow = followshipRepository.findByInitiatorAndTarget(username_initiator, username_target);

        follow.setNotifyOnMessage(notify);
                
        followshipRepository.save(follow);

        return "Message notification for " + username_target + (notify == 1 ? "Enabled" : "Disabled"); 
    }

    public String editNotificationsOnPost(String username_initiator, String username_target, int notify) {
        Followship follow = followshipRepository.findByInitiatorAndTarget(username_initiator, username_target);

        follow.setNotifyOnPost(notify);
                
        followshipRepository.save(follow);

        return "Post notification for " + username_target + (notify == 1 ? "Enabled" : "Disabled"); 
    }

    public void makePostNotification(String user) {
        List<Followship> followshipList = followshipRepository.findByTargetAndNotifyOnPost(user, 1);

        for (Followship followship : followshipList) {
            Notification postNotification = new Notification();

            postNotification.setContent("New post made");
            postNotification.setMaker(user);
            postNotification.setTimestamp(new Date());
            String followshipUsername = followship.getInitiator();
            postNotification.setRecipient(followshipUsername);
            template.convertAndSendToUser(followshipUsername, "/unread", notificationRepository.save(postNotification));
        }
    }

    public void makeMsgNotification(String sender, String receiver) {
        Followship followship = followshipRepository.
                findByInitiatorAndTargetAndNotifyOnMessage(receiver, sender, 1);
        if (followship == null) return;
        Notification msgNotification = new Notification();
        msgNotification.setContent("Unread message");
        msgNotification.setMaker(sender);
        msgNotification.setTimestamp(new Date());
        msgNotification.setRecipient(receiver);
        template.convertAndSendToUser(receiver, "/unread", notificationRepository.save(msgNotification));
    }

    public List<Notification> findAllUserNotifications(String user) {
        return notificationRepository.findByRecipient(user);
    }

    @Transactional
    public List<Notification> clearUserNotification(String user) {
        List<Notification> notifications = notificationRepository.findByRecipient(user);
        notificationRepository.deleteAllByRecipient(user);
        return notifications;
    }
}
