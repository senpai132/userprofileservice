package com.profilemanagement.controllers;

import com.profilemanagement.model.Followship;
import com.profilemanagement.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.profilemanagement.helper.dto.FollowResponseDTO;
import com.profilemanagement.services.NotificationsService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    NotificationsService notificationsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    @GetMapping("/getnotificationsettings/{initiator}/{target}")
    public ResponseEntity<FollowResponseDTO> GetUserNotificationSettings(@PathVariable String initiator, @PathVariable String target) {
        LOGGER.info("Retrieving user " + initiator + " notification setting for user " + target);
        Followship followship = notificationsService.getNotificationSettings(initiator, target);
        FollowResponseDTO dto = new FollowResponseDTO();
        dto.setInitiator(initiator);
        dto.setTarget(target);
        dto.setNotifyOnMessage(followship.getNotifyOnMessage());
        dto.setNotifyOnPost(followship.getNotifyOnPost());
        return new ResponseEntity<FollowResponseDTO> (dto, HttpStatus.OK);
    }

    @PutMapping("/editmsgnotification")
    public ResponseEntity<String> EditMsgNotification(@RequestBody FollowResponseDTO dto) {
        LOGGER.info("Changing user " + dto.getInitiator() + " notification setting for user " +
                dto.getTarget() + " when new message sent");
        return new ResponseEntity<> (notificationsService.editNotificationsOnMessage(dto.getInitiator(),
            dto.getTarget(), dto.getNotifyOnMessage()), HttpStatus.OK);        
    }

    @PutMapping("/editpostnotification")
    public ResponseEntity<String> EditPostNotification(@RequestBody FollowResponseDTO dto) {
        LOGGER.info("Changing user " + dto.getInitiator() + " notification setting for user " +
                dto.getTarget() + " when new post is made");
        return new ResponseEntity<>(notificationsService.editNotificationsOnPost(dto.getInitiator(),
            dto.getTarget(), dto.getNotifyOnPost()), HttpStatus.OK);        
    }

    @PostMapping("/new-post-notification/{user}")
    public ResponseEntity<?> MakePostNotification(@PathVariable String user) {
        LOGGER.info("Creating new notification for new post made by user " + user);
        notificationsService.makePostNotification(user);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @PostMapping("new-msg-notification/{sender}/{receiver}")
    public ResponseEntity<?> MakeMsgNotification(@PathVariable String sender, @PathVariable String receiver) {
        LOGGER.info("Creating new notification for new message sent by user " + sender + " to user " + receiver);
        notificationsService.makeMsgNotification(sender, receiver);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @GetMapping("/{user}")
    public ResponseEntity<List<Notification>> FindAllUserNotification(@PathVariable String user) {
        LOGGER.info("Retrieving all unread notification for user " + user);
        return new ResponseEntity<> (notificationsService.findAllUserNotifications(user), HttpStatus.OK);
    }

    @DeleteMapping("/{user}")
    public ResponseEntity<List<Notification>> ClearUserNotification(@PathVariable String user) {
        LOGGER.info("Deleting all read notification for user " + user);
        return new ResponseEntity<> (notificationsService.clearUserNotification(user), HttpStatus.OK);
    }
}
