package com.nghia.userservice.service;

import com.nghia.userservice.dto.MessageDTO;
import com.nghia.userservice.entity.User;
import java.util.Optional;

public interface MessageService {
  Optional<MessageDTO> messageActive(User user, String linkActive);
}
