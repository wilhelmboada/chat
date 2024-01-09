package com.chat.service.repository.converter;


import com.chat.service.domain.model.Message;
import com.chat.service.repository.data.MessageEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MessageConverterTest {

    @Test
    void testToEntity() {

        long userId = 1L;
        long conversationId = 2L;
        String text = "Hello, World!";
        Message message = new Message(userId, conversationId, text);

        MessageEntity entity = MessageConverter.toEntity(message);

        assertNotNull(entity);
        assertEquals(userId, entity.getUserId());
        assertEquals(conversationId, entity.getConversationId());
        assertEquals(text, entity.getText());
    }

    @Test
    void testToModel() {

        long userId = 1L;
        long conversationId = 2L;
        String text = "text";
        MessageEntity entity = new MessageEntity(userId, conversationId, text);

        Message convertedMessage = MessageConverter.toModel(entity);

        assertNotNull(convertedMessage);
        assertEquals(userId, convertedMessage.userId());
        assertEquals(conversationId, convertedMessage.conversationId());
        assertEquals(text, convertedMessage.text());
    }
}

