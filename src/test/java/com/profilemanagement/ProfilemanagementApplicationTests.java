package com.profilemanagement;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.profilemanagement.model.UserProfile;
import com.profilemanagement.repositories.UserProfileRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProfilemanagementApplicationTests {

	@Autowired
	private UserProfileRepository userRepository;

	@Test
	void testGetUserByUsername() {
		UserProfile u = userRepository.findByUsername("uki");
		assertThat(u).isNotNull();
	}

	@Test
	void testGetAllUsers() {
		List<UserProfile> users = userRepository.findAll();
		assertThat(users.size()).isGreaterThan(0);
	}
}
