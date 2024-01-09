package com.chat.service.controller;

import com.chat.service.controller.factory.ConversationConverter;
import com.chat.service.controller.dto.ConversationDTO;
import com.chat.service.controller.dto.UserDTO;
import com.chat.service.domain.usecase.ConversationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/conversations")
public class ConversationController {

    private final ConversationUseCase conversationUseCase;

    public ConversationController(ConversationUseCase conversationUseCase) {
        this.conversationUseCase = conversationUseCase;
    }

    @PostMapping("creation")
    public ResponseEntity<UserDTO> save(@RequestBody ConversationDTO conversationDTO) {
        conversationUseCase.createConversation(ConversationConverter.toModel(conversationDTO), conversationDTO.getUsers());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
