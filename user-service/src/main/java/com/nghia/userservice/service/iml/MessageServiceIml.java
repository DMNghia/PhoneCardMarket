package com.nghia.userservice.service.iml;

import com.nghia.userservice.dto.MessageDTO;
import com.nghia.userservice.entity.Message;
import com.nghia.userservice.entity.User;
import com.nghia.userservice.repository.MessageRepository;
import com.nghia.userservice.service.MessageService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
public class MessageServiceIml implements MessageService {

  private final MessageRepository messageRepository;
  private final ModelMapper modelMapper;

  public MessageServiceIml(MessageRepository messageRepository, ModelMapper modelMapper) {
    this.messageRepository = messageRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public Optional<MessageDTO> messageActive(User user, String token) {
    String linkActive = "http://localhost:8080/api/auth/activeAccount?username="
        + user.getUsername() + "&activeToken=" + token;
    String subject = "[THÔNG BÁO] MÃ KÍCH HOẠT TÀI KHOẢN";
    String content = "Cảm ơn đã đăng ký tài khoản tại trang web của chúng tôi!\n"
        + "Để sử dụng các dịch vụ và tiện ích chúng tôi bạn phải kích hoạt tài khoản của mình với mã kích hoạt dưới đây:\n"
        + linkActive
        + "\n(Lưu ý mã kích hoạt chỉ có hiệu lực trong vòng một ngày kể từ khi bạn nhận được thông báo này, "
        + "và mã kích hoạt chỉ được sử dụng một lần duy nhất!)";

    Message newMessage = Message.builder()
        .toEmail(user.getEmail())
        .createdAt(LocalDateTime.now())
        .isSent(false)
        .subject(subject)
        .content(content)
        .build();
    Message message = messageRepository.save(newMessage);
    if (ObjectUtils.isEmpty(message)) {
      return Optional.empty();
    }
    return Optional.ofNullable(modelMapper
        .map(message, MessageDTO.class));
  }
}
