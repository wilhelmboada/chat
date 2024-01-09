package com.chat.service.controller;

import com.chat.service.controller.factory.MessageConverter;
import com.chat.service.controller.dto.MessageDTO;
import com.chat.service.domain.usecase.MessageUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageUseCase messageUseCase;

    public MessageController(MessageUseCase messageUseCase) {
        this.messageUseCase = messageUseCase;
    }

    @PostMapping("send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO) {
        messageUseCase.sendMessage(MessageConverter.toModel(messageDTO));
        return ResponseEntity.ok("Message sent successfully.");
    }
}
