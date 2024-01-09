package com.chat.service.repository.converter;

import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;
import com.chat.service.repository.data.ConversationEntity;
import com.chat.service.repository.data.UserEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class ConversationConverter {

    private ConversationConverter() {

    }

    public static ConversationEntity toEntity(Conversation conversation) {
        return new ConversationEntity(conversation.conversationId());
    }

    public static ConversationEntity toEntity(Conversation conversation, Set<User> users) {
        ConversationEntity conversationEntity = new ConversationEntity(conversation.conversationId());
        users.forEach(user -> conversationEntity.addUsers(UserConverter.toEntity(user)));
        return conversationEntity;
    }

    public static Conversation toModel(ConversationEntity conversation) {
        return new Conversation(conversation.getConversationId(), Set.of());
    }
}
