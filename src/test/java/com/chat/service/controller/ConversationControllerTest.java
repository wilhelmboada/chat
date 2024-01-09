package com.chat.service.controller;

import com.chat.service.controller.dto.ConversationDTO;
import com.chat.service.controller.dto.UserDTO;
import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.usecase.ConversationUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.mockito.Mockito.*;

class ConversationControllerTest {

    @Mock
    private ConversationUseCase conversationUseCase;

    @InjectMocks
    private ConversationController conversationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveConversation() {
        ConversationDTO conversationDTO = new ConversationDTO(1L, Set.of());
        when(conversationUseCase.createConversation(buildConversation(), Set.of())).thenReturn(buildConversation());

        ResponseEntity<UserDTO> responseEntity = conversationController.save(conversationDTO);

        verify(conversationUseCase, times(1)).createConversation(any(), any());
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    private Conversation buildConversation() {
        return new Conversation(1L, Set.of());
    }
}
