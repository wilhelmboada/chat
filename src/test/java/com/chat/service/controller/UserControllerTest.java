package com.chat.service.controller;

import com.chat.service.controller.dto.UserConversationDTO;
import com.chat.service.controller.dto.UserDTO;
import com.chat.service.domain.model.User;
import com.chat.service.domain.usecase.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserUseCase userUseCase;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        UserDTO userDTO = new UserDTO(1L, "userName");
        User user = new User(1L, "userName", Set.of());
        when(userUseCase.createUser(user)).thenReturn(user);

        ResponseEntity<UserDTO> responseEntity = userController.save(userDTO);

        verify(userUseCase, times(1)).createUser(user);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userDTO, responseEntity.getBody());
    }

    @Test
    void testJoinConversation() {
        Long userId = 1L;
        Long conversationId = 2L;
        doNothing().when(userUseCase).joinConversation(userId, conversationId);

        ResponseEntity<String> responseEntity = userController.joinConversation(userId, conversationId);

        verify(userUseCase, times(1)).joinConversation(userId, conversationId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User joined the conversation successfully.", responseEntity.getBody());
    }

    @Test
    void testGetUserConversations() {
        Long userId = 1L;
        User user = new User(userId, "userName", Set.of());
        when(userUseCase.getUser(userId)).thenReturn(user);

        ResponseEntity<UserConversationDTO> responseEntity = userController.getUserConversations(userId);

        verify(userUseCase, times(1)).getUser(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}

