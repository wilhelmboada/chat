package com.chat.service.controller.factory;

import com.chat.service.controller.dto.MessageDTO;
import com.chat.service.domain.model.Message;

public class MessageConverter {

    private MessageConverter() {

    }

    public static Message toModel(MessageDTO messageDTO) {
        return new Message(
                messageDTO.getUserId(),
                messageDTO.getConversationId(),
                messageDTO.getText());
    }
}
