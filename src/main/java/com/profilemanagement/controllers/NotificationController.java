package com.profilemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profilemanagement.helper.dto.FollowResponseDTO;
import com.profilemanagement.services.NotificationsService;

@RestController
@CrossOrigin
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    NotificationsService notificationsService;

    @PutMapping("/editmsgnotification")
    public ResponseEntity<String> EditMsgNotification(@RequestBody FollowResponseDTO dto) {         
        return new ResponseEntity<> (notificationsService.editNotificationsOnMessage(dto.getIntiator(), 
            dto.getTarget(), dto.getNotifyOnMessage()), HttpStatus.OK);        
    }

    @PutMapping("/editpostnotification")
    public ResponseEntity<String> EditPostNotification(@RequestBody FollowResponseDTO dto) {         
        return new ResponseEntity<>(notificationsService.editNotificationsOnPost(dto.getIntiator(), 
            dto.getTarget(), dto.getNotifyOnPost()), HttpStatus.OK);        
    }
    
}
