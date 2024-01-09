package com.chat.service.controller.factory;

import com.chat.service.controller.dto.UserConversationDTO;
import com.chat.service.controller.dto.UserDTO;
import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter {

    private UserConverter() {

    }

    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .userId(user.userId())
                .userName(user.userName())
                .build();
    }

    public static UserConversationDTO toUserConversationDTO(User user) {
        Set<Long> conversations = user.conversations()
                .stream()
                .map(Conversation::conversationId)
                .collect(Collectors.toSet());
        return UserConversationDTO.builder()
                .userId(user.userId())
                .userName(user.userName())
                .conversationIds(conversations)
                .build();
    }

    public static User toModel(UserDTO user) {
        return new User(user.getUserId(), user.getUserName(), Set.of());
    }
}
