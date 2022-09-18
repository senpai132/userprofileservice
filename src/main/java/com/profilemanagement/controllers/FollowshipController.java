package com.profilemanagement.controllers;

import java.util.List;

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
import com.profilemanagement.services.FollowshipService;

@RestController
@CrossOrigin
@RequestMapping("/followship")
public class FollowshipController {
    @Autowired
    FollowshipService followshipService;

    private UserProfileMapper userProfileMapper;

    @PutMapping("/block/{initiator}")
    public ResponseEntity<String> BlockUser(@PathVariable String initiator, @RequestBody ProfileDetailsDTO target) {         
        return new ResponseEntity<>(followshipService.blockUser(initiator, target.getValue()), HttpStatus.OK);        
    }

    @PutMapping("/unblock/{initiator}")
    public ResponseEntity<String> UnblockUser(@PathVariable String initiator, @RequestBody ProfileDetailsDTO target) {         
        return new ResponseEntity<>(followshipService.unblockUser(initiator, target.getValue()), HttpStatus.OK);        
    }

    @GetMapping("/isblocked/{initiator}/{target}")
    public ResponseEntity<Boolean> isBlockedUser(@PathVariable String initiator, @PathVariable String target) {         
        return new ResponseEntity<>(followshipService.isUserBlocked(initiator, target), HttpStatus.OK);        
    }

    @GetMapping("/isfollowed/{initiator}/{target}")
    public ResponseEntity<Boolean> isFollowedUser(@PathVariable String initiator, @PathVariable String target) {         
        return new ResponseEntity<>(followshipService.isUserFollowed(initiator, target), HttpStatus.OK);        
    }

    @GetMapping("/getfollowed/{initiator}")
    public ResponseEntity<List<UserProfileDTO>> returnFollowedUsers(@PathVariable String initiator) {         
        return new ResponseEntity<>(userProfileMapper.toDtoList(followshipService.findAllFollowedUsers(initiator)), 
            HttpStatus.OK);        
    }

    @PutMapping("/follow/{initiator}")
    public ResponseEntity<String> FollowUser(@PathVariable String initiator, @RequestBody ProfileDetailsDTO target) {         
        return new ResponseEntity<>(followshipService.followUser(initiator, target.getValue()), HttpStatus.OK);        
    }

    @PutMapping("/followresponse")
    public ResponseEntity<String> FollowResponse(@RequestBody FollowResponseDTO dto) {         
        return new ResponseEntity<>(followshipService.AnswerFollowRequest(dto.getIntiator(), 
            dto.getTarget(), dto.getResponse()), HttpStatus.OK);        
    }

    @DeleteMapping("/unfollow/{initiator}")
    public ResponseEntity<String> UnfollowUser(@PathVariable String initiator, @RequestBody ProfileDetailsDTO target) {
        return new ResponseEntity<>(followshipService.UnfollowUser(initiator, target.getValue()), HttpStatus.OK);
    }

    public FollowshipController() {
        this.userProfileMapper = new UserProfileMapper();
    }
    
}