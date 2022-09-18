package com.profilemanagement.helper.mappers;

import java.util.ArrayList;
import java.util.List;

import com.profilemanagement.helper.dto.UserProfileDTO;
import com.profilemanagement.model.UserProfile;

public class UserProfileMapper implements MapperInterface<UserProfile, UserProfileDTO>{

    @Override
    public UserProfile toEntity(UserProfileDTO dto) {
        UserProfile userprofile = new UserProfile();
        //userprofile.setId(dto.getId());
        userprofile.setUsername(dto.getUsername());
        userprofile.setEmail(dto.getEmail());
        userprofile.setName(dto.getName());
        userprofile.setGender(dto.getGender());
        userprofile.setBiography(dto.getBiography());
        userprofile.setBirthDate(dto.getBirthDate());
        userprofile.setPhoneNumber(dto.getPhoneNumber());
        
        return userprofile;
    }

    @Override
    public List<UserProfile> toEntityList(List<UserProfileDTO> dtoList) {
        return null;
    }

    @Override
    public UserProfileDTO toDto(UserProfile entity) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setName(entity.getName());
        dto.setBirthDate(entity.getBirthDate());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setGender(entity.getGender());
        dto.setUsername(entity.getUsername());
        dto.setBiography(entity.getBiography());
        dto.setEducation(entity.getEducation());
        dto.setHobbies(entity.getHobbies());
        dto.setSkills(entity.getSkills());
        dto.setWorkExperience(entity.getWorkExperience());
        
        return dto;
    }

    @Override
    public List<UserProfileDTO> toDtoList(List<UserProfile> entityList) {
        List<UserProfileDTO> dtoList = new ArrayList<UserProfileDTO>();
        for(UserProfile el : entityList) {
            dtoList.add(toDto(el));
        }

        return dtoList;
    }
    
}
