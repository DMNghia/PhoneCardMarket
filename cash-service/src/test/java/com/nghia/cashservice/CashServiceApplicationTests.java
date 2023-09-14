package com.nghia.cashservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nghia.cashservice.dto.UserResponseDto;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CashServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) throws Exception{
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
			objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			UserResponseDto userResponseDto = UserResponseDto.builder()
					.username("Nghia")
					.createdAt(LocalDateTime.now())
					.email("test@gmail.com")
					.roleName("USER")
					.build();
			System.out.println(objectMapper.writeValueAsString(userResponseDto));
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
    }
  }

}
