package com.nghia.userservice.config;

import com.nghia.userservice.dto.MessageDTO;
import com.nghia.userservice.entity.Message;
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

    // Message -> MessageDTO
    modelMapper.addMappings(new PropertyMap<Message, MessageDTO>() {
      @Override
      protected void configure() {
        map().setId(source.getId());
        map().setTo(source.getToEmail());
        map().setSubject(source.getSubject());
        map().setContent(source.getContent());
      }
    });

    // MessageDTO -> Message
    modelMapper.addMappings(new PropertyMap<MessageDTO, Message>() {
      @Override
      protected void configure() {
        map().setId(source.getId());
        map().setToEmail(source.getTo());
        map().setSubject(source.getSubject());
        map().setContent(source.getContent());
      }
    });
    return modelMapper;
  }
}
