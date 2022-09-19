package com.profilemanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profilemanagement.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, String>{
    UserProfile findByUsername(String username);
    UserProfile findById(Integer id);
    List<UserProfile> findByVisibility(int visibility);
}
