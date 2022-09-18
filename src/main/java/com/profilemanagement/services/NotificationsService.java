package com.profilemanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profilemanagement.model.Followship;
import com.profilemanagement.repositories.FollowshipRepository;

@Service
public class NotificationsService {
    @Autowired
    FollowshipRepository followshipRepository;

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
    
}
