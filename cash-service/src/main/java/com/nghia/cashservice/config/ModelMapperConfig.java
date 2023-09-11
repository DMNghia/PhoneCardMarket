package com.nghia.cashservice.config;

import com.nghia.cashservice.dto.UserResponseDto;
import com.nghia.cashservice.dto.response.BaseResponse;
import com.nghia.grpc.entities.user.FindUserResponse;
import java.time.LocalDateTime;
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

    modelMapper.addMappings(new PropertyMap<FindUserResponse, UserResponseDto>() {
      @Override
      protected void configure() {
        map().setId(source.getId());
        map().setUsername(source.getUsername());
        map().setEmail(source.getEmail());
        map().setEnable(source.getIsEnable());
        map().setLocked(source.getIsLocked());
        map().setRoleName(source.getRole().name());
      }
    });

    modelMapper.addMappings(new PropertyMap<com.nghia.grpc.entities.user.BaseResponse, BaseResponse<UserResponseDto>>() {
      @Override
      protected void configure() {
        map()
      }
    })
    return modelMapper;
  }
}
