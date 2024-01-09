package com.chat.service.controller;

import com.chat.service.controller.factory.UserConverter;
import com.chat.service.controller.dto.UserConversationDTO;
import com.chat.service.controller.dto.UserDTO;
import com.chat.service.domain.model.User;
import com.chat.service.domain.usecase.UserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping("creation")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        User user = userUseCase.createUser(UserConverter.toModel(userDTO));
        return new ResponseEntity<>(UserConverter.toUserDTO(user), HttpStatus.CREATED);
    }

    @PostMapping("{userId}/join/{conversationId}")
    public ResponseEntity<String> joinConversation(@PathVariable Long userId, @PathVariable Long conversationId) {
        userUseCase.joinConversation(userId, conversationId);
        return ResponseEntity.ok("User joined the conversation successfully.");
    }

    @GetMapping("{userId}/conversations")
    public ResponseEntity<UserConversationDTO> getUserConversations(@PathVariable Long userId) {
        return ResponseEntity.ok(UserConverter.toUserConversationDTO(userUseCase.getUser(userId)));
    }
}
