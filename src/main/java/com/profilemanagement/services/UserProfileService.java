package com.profilemanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profilemanagement.helper.dto.ProfileDetailsDTO;
import com.profilemanagement.model.UserProfile;
import com.profilemanagement.repositories.UserProfileRepository;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    public String manageProfileVisibility(String username, ProfileDetailsDTO details) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);

        userProfile.setVisibility(Integer.parseInt(details.getValue()));

        userProfileRepository.save(userProfile);

        return "Profile visibility successfully set to " + (Integer.parseInt(details.getValue()) == 0 ? "Public" : "Private");
    }

    public List<UserProfile> searchProfiles(String searchCriteria) {
        List<UserProfile> allUserProfiles = userProfileRepository.findAll();
        List<UserProfile> foundUsers = new ArrayList<UserProfile>();

        for(UserProfile el : allUserProfiles) {
            if(el.getUsername().toLowerCase().contains(searchCriteria.toLowerCase()))
                foundUsers.add(el);
        }

        return foundUsers;
    }

    public List<UserProfile> searchPublicProfiles(String searchCriteria) {
        List<UserProfile> allUserProfiles = userProfileRepository.findAll();
        List<UserProfile> foundUsers = new ArrayList<UserProfile>();

        for(UserProfile el : allUserProfiles) {
            if(el.getUsername().toLowerCase().contains(searchCriteria.toLowerCase()) && el.getVisibility()==0)
                foundUsers.add(el);
        }

        return foundUsers;
    }

    public List<String> retreiveAllPublicProfiles() {
        List<UserProfile> allUserProfiles = userProfileRepository.findByVisibility(0);
        List<String> foundUsers = new ArrayList<String>();

        for(UserProfile el : allUserProfiles) {
            foundUsers.add(el.getUsername());
        }

        return foundUsers;
    }

    public String addUserProfile(UserProfile userprofile) {
        if(userProfileRepository.findByUsername(userprofile.getUsername()) != null) {
            return "Error: Username already exists";
        }

        userProfileRepository.save(userprofile);

        return "User profile added successfully";
    }

    public String editUserProfile(String username, UserProfile updatedUserProfile) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);

        if(!username.equals(updatedUserProfile.getUsername()) &&
            (userProfileRepository.findByUsername(updatedUserProfile.getUsername()) != null)) {
                return "Error: Username already exists";
        }

        userProfile.setUsername(updatedUserProfile.getUsername());
        userProfile.setBirthDate(updatedUserProfile.getBirthDate());
        userProfile.setEmail(updatedUserProfile.getEmail());
        userProfile.setBiography(updatedUserProfile.getBiography());
        userProfile.setGender(updatedUserProfile.getGender());
        userProfile.setPhoneNumber(updatedUserProfile.getPhoneNumber());
        userProfile.setName(updatedUserProfile.getName());

        userProfileRepository.save(userProfile);

        return "User profile edited successfully";
    }

    public String editUserExperience(String username, ProfileDetailsDTO details) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);
        userProfile.setWorkExperience(details.getValue());

        userProfileRepository.save(userProfile);

        return "User work experience edited successfully";
    }

    public String editUserEducation(String username, ProfileDetailsDTO details) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);
        userProfile.setEducation(details.getValue());

        userProfileRepository.save(userProfile);

        return "User education edited successfully";
    }

    public String editUserSkills(String username, ProfileDetailsDTO details) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);
        userProfile.setSkills(details.getValue());

        userProfileRepository.save(userProfile);

        return "User skills edited successfully";
    }

    public String editUserHobbies(String username, ProfileDetailsDTO details) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);
        userProfile.setHobbies(details.getValue());

        userProfileRepository.save(userProfile);

        return "User hobbies edited successfully";
    }

}
