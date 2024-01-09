package com.chat.service.controller.factory;

import com.chat.service.controller.dto.ConversationDTO;
import com.chat.service.domain.model.Conversation;

import java.util.Set;

public class ConversationConverter {

    private ConversationConverter() {

    }

    public static Conversation toModel(ConversationDTO conversationDTO) {
        return new Conversation(conversationDTO.getConversationId(), Set.of());
    }
}
