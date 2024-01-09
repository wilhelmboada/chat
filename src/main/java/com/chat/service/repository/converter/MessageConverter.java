package com.chat.service.repository.converter;

import com.chat.service.domain.model.Message;
import com.chat.service.repository.data.MessageEntity;


public class MessageConverter {

    private MessageConverter() {

    }

    public static MessageEntity toEntity(Message message) {
        return new MessageEntity(message.userId(), message.conversationId(), message.text());
    }

    public static Message toModel(MessageEntity messageEntity) {
        return new Message(
                messageEntity.getUserId(),
                messageEntity.getConversationId(),
                messageEntity.getText());
    }

}
