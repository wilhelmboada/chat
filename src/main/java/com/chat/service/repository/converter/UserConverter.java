package com.chat.service.repository.converter;

import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;
import com.chat.service.repository.data.UserEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter {

    private UserConverter() {

    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(user.userId(), user.userName());
    }

    public static UserEntity toEntity(User user, Conversation conversation) {
        UserEntity userEntity = new UserEntity(
                user.userId(), user.userName());
        userEntity.addConversation(ConversationConverter.toEntity(conversation));
        return userEntity;
    }

    public static User toModel(UserEntity user) {
        Set<Conversation> conversations = user.getConversations()
                .stream()
                .map(ConversationConverter::toModel)
                .collect(Collectors.toSet());
        return new User(user.getUserId(), user.getUserName(), conversations);
    }
}
