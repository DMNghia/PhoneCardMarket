package com.nghia.cashservice.config;

import com.nghia.cashservice.dto.UserResponseDto;
import com.nghia.cashservice.dto.response.ResponseInfo;
import com.nghia.grpc.entities.user.FindUserResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);

    return modelMapper;
  }
}
