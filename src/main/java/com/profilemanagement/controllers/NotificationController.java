package com.profilemanagement.controllers;

import com.profilemanagement.model.Followship;
import com.profilemanagement.model.Notification;
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

    @GetMapping("/getnotificationsettings/{initiator}/{target}")
    public ResponseEntity<FollowResponseDTO> GetUserNotificationSettings(@PathVariable String initiator, @PathVariable String target) {
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
        return new ResponseEntity<> (notificationsService.editNotificationsOnMessage(dto.getInitiator(),
            dto.getTarget(), dto.getNotifyOnMessage()), HttpStatus.OK);        
    }

    @PutMapping("/editpostnotification")
    public ResponseEntity<String> EditPostNotification(@RequestBody FollowResponseDTO dto) {         
        return new ResponseEntity<>(notificationsService.editNotificationsOnPost(dto.getInitiator(),
            dto.getTarget(), dto.getNotifyOnPost()), HttpStatus.OK);        
    }

    @PostMapping("/new-post-notification/{user}")
    public ResponseEntity<?> MakePostNotification(@PathVariable String user) {
        notificationsService.makePostNotification(user);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @PostMapping("new-msg-notification/{sender}/{receiver}")
    public ResponseEntity<?> MakeMsgNotification(@PathVariable String sender, @PathVariable String receiver) {
        notificationsService.makeMsgNotification(sender, receiver);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @GetMapping("/{user}")
    public ResponseEntity<List<Notification>> FindAllUserNotification(@PathVariable String user) {
        return new ResponseEntity<> (notificationsService.findAllUserNotifications(user), HttpStatus.OK);
    }

    @DeleteMapping("/{user}")
    public ResponseEntity<List<Notification>> ClearUserNotification(@PathVariable String user) {
        return new ResponseEntity<> (notificationsService.clearUserNotification(user), HttpStatus.OK);
    }
}
