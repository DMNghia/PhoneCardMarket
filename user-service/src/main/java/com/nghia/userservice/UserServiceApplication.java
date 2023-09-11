package com.nghia.userservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	NewTopic mail() {
		// topic name , partition numbers, replication number
		return new NewTopic("mail", 2, (short) 3);
	}

	@Bean
	NewTopic notification() {
		return new NewTopic("notification", 2, (short) 3);
	}

}
