package com.chat.service.controller;

import com.chat.service.controller.dto.MessageDTO;
import com.chat.service.domain.model.Message;
import com.chat.service.domain.usecase.MessageUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MessageControllerTest {

    @Mock
    private MessageUseCase messageUseCase;

    @InjectMocks
    private MessageController messageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {

        MessageDTO messageDTO = new MessageDTO(1L, 1L, "Text");

        ResponseEntity<String> responseEntity = messageController.sendMessage(messageDTO);

        verify(messageUseCase).sendMessage(new Message(1L, 1L, "Text"));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Message sent successfully.", responseEntity.getBody());
    }
}
