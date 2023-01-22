package com.profilemanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profilemanagement.helper.dto.FollowResponseDTO;
import com.profilemanagement.helper.dto.ProfileDetailsDTO;
import com.profilemanagement.helper.dto.UserProfileDTO;
import com.profilemanagement.helper.mappers.UserProfileMapper;
import com.profilemanagement.model.UserProfile;
import com.profilemanagement.services.FollowshipService;

@RestController
@CrossOrigin
@RequestMapping("/followship")
public class FollowshipController {
    @Autowired
    FollowshipService followshipService;

    private UserProfileMapper userProfileMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(FollowshipController.class);

    @PutMapping("/block/{initiator}")
    public ResponseEntity<String> BlockUser(@PathVariable String initiator, @RequestBody ProfileDetailsDTO target) {
        LOGGER.info("User " + initiator + " has blocked user " + target.getValue());
        return new ResponseEntity<>(followshipService.blockUser(initiator, target.getValue()), HttpStatus.OK);
    }

    @PutMapping("/unblock/{initiator}")
    public ResponseEntity<String> UnblockUser(@PathVariable String initiator, @RequestBody ProfileDetailsDTO target) {
        LOGGER.info("User " + initiator + " has unblocked user " + target.getValue());
        return new ResponseEntity<>(followshipService.unblockUser(initiator, target.getValue()), HttpStatus.OK);
    }

    @GetMapping("/isblocked/{initiator}/{target}")
    public ResponseEntity<Boolean> isBlockedUser(@PathVariable String initiator, @PathVariable String target) {
        LOGGER.info("Checking if user " + initiator + " is blocked by user " + target);
        return new ResponseEntity<>(followshipService.isUserBlocked(initiator, target), HttpStatus.OK);
    }

    @GetMapping("/isfollowed/{initiator}/{target}")
    public ResponseEntity<Boolean> isFollowedUser(@PathVariable String initiator, @PathVariable String target) {
        LOGGER.info("Checking if user " + initiator + " is follower of user " + target);
        return new ResponseEntity<>(followshipService.isUserFollowed(initiator, target), HttpStatus.OK);
    }

    @GetMapping("/getfollowed/{initiator}")
    public ResponseEntity<List<UserProfileDTO>> returnFollowedUsers(@PathVariable String initiator) {
        LOGGER.info("Returning all follower of user " + initiator);
        return new ResponseEntity<>(userProfileMapper.toDtoList(followshipService.findAllConnectedUsers(initiator)),
            HttpStatus.OK);        
    }

    @GetMapping("/getallpending/{initiator}")
    public ResponseEntity<List<UserProfileDTO>> returnPending(@PathVariable String initiator) {
        LOGGER.info("Returning all pending follow request of user " + initiator);
        return new ResponseEntity<>(userProfileMapper.toDtoList(followshipService.findAllPendingFollows(initiator)),
            HttpStatus.OK);        
    }

    @GetMapping("/findallfollowedusernames/{username}")
    public ResponseEntity<List<String>> findAllPublicProfiles(@PathVariable String username) {
        LOGGER.info("Finding all users the user " + username + " is following");
        List<UserProfile> users = followshipService.findAllFollowedUsers(username);
        List<String> result = new ArrayList<>();
        for(UserProfile user : users){
            result.add(user.getUsername());
        }
        
        return new ResponseEntity<List<String>>(result, HttpStatus.OK);
    }

    @PutMapping("/follow/{initiator}")
    public ResponseEntity<String> FollowUser(@PathVariable String initiator, @RequestBody ProfileDetailsDTO target) {
        LOGGER.info("User " + initiator + " has started following user " + target.getValue());
        return new ResponseEntity<>(followshipService.followUser(initiator, target.getValue()), HttpStatus.OK);
    }

    @PutMapping("/followresponse")
    public ResponseEntity<String> FollowResponse(@RequestBody FollowResponseDTO dto) {
        LOGGER.info("User " + dto.getInitiator() + " has responded to follow request from user " + dto.getTarget()
         + " with answer " + (dto.getResponse() == 1 ? "accept" : "decline"));
        return new ResponseEntity<>(followshipService.AnswerFollowRequest(dto.getInitiator(),
            dto.getTarget(), dto.getResponse()), HttpStatus.OK);        
    }

    @DeleteMapping("/unfollow/{initiator}")
    public ResponseEntity<String> UnfollowUser(@PathVariable String initiator, @RequestBody ProfileDetailsDTO target) {
        LOGGER.info("User " + initiator + " has stopped following user " + target.getValue());
        return new ResponseEntity<>(followshipService.UnfollowUser(initiator, target.getValue()), HttpStatus.OK);
    }

    public FollowshipController() {
        this.userProfileMapper = new UserProfileMapper();
    }
    
}
