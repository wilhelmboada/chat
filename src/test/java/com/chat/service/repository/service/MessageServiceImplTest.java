package com.chat.service.repository.service;

import com.chat.service.domain.model.Message;
import com.chat.service.repository.MessageRepository;
import com.chat.service.repository.data.MessageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMessage() {

        long userId = 2L;
        long conversationId = 3L;
        String text = "Text";
        Message message = new Message(userId, conversationId, text);
        MessageEntity savedEntity = new MessageEntity(userId, conversationId, text);

        when(messageRepository.save(any(MessageEntity.class))).thenReturn(savedEntity);

        Message savedMessage = messageService.save(message);

        verify(messageRepository).save(any(MessageEntity.class));
        assertNotNull(savedMessage);
        assertEquals(userId, savedMessage.userId());
        assertEquals(conversationId, savedMessage.conversationId());
        assertEquals(text, savedMessage.text());
    }

}