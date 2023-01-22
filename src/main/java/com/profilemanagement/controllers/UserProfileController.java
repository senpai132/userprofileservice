package com.profilemanagement.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profilemanagement.helper.dto.ProfileDetailsDTO;
import com.profilemanagement.helper.dto.UserProfileDTO;
import com.profilemanagement.helper.mappers.UserProfileMapper;
import com.profilemanagement.model.UserProfile;
import com.profilemanagement.services.UserProfileService;

@RestController
@CrossOrigin
@RequestMapping("/userprofile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    private UserProfileMapper userProfileMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileController.class);
    
    @GetMapping("/find/{searchCriteria}")
    public ResponseEntity<List<UserProfileDTO>> findAllProfiles(@PathVariable String searchCriteria) {
        LOGGER.info("Finding users that fit criteria " + searchCriteria);
        return new ResponseEntity<List<UserProfileDTO>>(userProfileMapper.toDtoList(
            userProfileService.searchProfiles(searchCriteria)), HttpStatus.OK);
    }

    @GetMapping("/findallpublicusers")
    public ResponseEntity<List<String>> findAllPublicProfiles() {
        LOGGER.info("Finding all public user profiles");
        return new ResponseEntity<List<String>>((userProfileService.retreiveAllPublicProfiles()), HttpStatus.OK);
    }

    @GetMapping("/findpubliconly/{searchCriteria}")
    public ResponseEntity<List<UserProfileDTO>> findPublicProfiles(@PathVariable String searchCriteria) {
        LOGGER.info("Finding public users that fit criteria " + searchCriteria);
        return new ResponseEntity<List<UserProfileDTO>>(userProfileMapper.toDtoList(
            userProfileService.searchPublicProfiles(searchCriteria)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
        LOGGER.info("Adding new user profile " + userProfileDTO.username);
        UserProfile userProfile = userProfileMapper.toEntity(userProfileDTO);        

        return new ResponseEntity<>(userProfileService.addUserProfile(userProfile), HttpStatus.CREATED);        
    }

    @PutMapping("/edit/{username}")
    public ResponseEntity<String> editUserProfile(@PathVariable String username, @RequestBody UserProfileDTO userProfileDTO) {
        LOGGER.info("Editing user profile with username " + userProfileDTO.username);
        UserProfile userProfile = userProfileMapper.toEntity(userProfileDTO);        

        return new ResponseEntity<>(userProfileService.editUserProfile(username, userProfile), HttpStatus.OK);         
    }

    @PutMapping("/editvisibility/{username}")
    public ResponseEntity<String> editUserVisibility(@PathVariable String username, @RequestBody ProfileDetailsDTO dto) {
        LOGGER.info("Changing user visibility status for profile with username " + username);
        return new ResponseEntity<>(userProfileService.manageProfileVisibility(username, dto), HttpStatus.OK);
    }

    @PutMapping("/editexperience/{username}")
    public ResponseEntity<String> editUserExperience(@PathVariable String username, @RequestBody ProfileDetailsDTO dto) {
        LOGGER.info("Editing user profile experience for profile with username " + username);
        return new ResponseEntity<>(userProfileService.editUserExperience(username, dto), HttpStatus.OK);
    }

    @PutMapping("/editeducation/{username}")
    public ResponseEntity<String> editUserEducation(@PathVariable String username, @RequestBody ProfileDetailsDTO dto) {
        LOGGER.info("Editing user profile education for profile with username " + username);
        return new ResponseEntity<>(userProfileService.editUserEducation(username, dto), HttpStatus.OK);
    }

    @PutMapping("/editskills/{username}")
    public ResponseEntity<String> editUserSkills(@PathVariable String username, @RequestBody ProfileDetailsDTO dto) {
        LOGGER.info("Editing user profile skills for profile with username " + username);
        return new ResponseEntity<>(userProfileService.editUserSkills(username, dto), HttpStatus.OK);
    }

    @PutMapping("/edithobbies/{username}")
    public ResponseEntity<String> editUserHobbies(@PathVariable String username, @RequestBody ProfileDetailsDTO dto) {
        LOGGER.info("Editing user profile hobbies for profile with username " + username);
        return new ResponseEntity<>(userProfileService.editUserHobbies(username, dto), HttpStatus.OK);
    }

    public UserProfileController() {
        this.userProfileMapper = new UserProfileMapper();
    }
}
