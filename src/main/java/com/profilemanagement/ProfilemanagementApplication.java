package com.profilemanagement;

import io.opentracing.contrib.spring.cloud.websocket.WebsocketAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude= WebsocketAutoConfiguration.class)
public class ProfilemanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfilemanagementApplication.class, args);
	}

}
