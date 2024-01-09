package com.chat.service.domain.usecase;

import com.chat.service.domain.error.BusinessError;
import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;
import com.chat.service.domain.service.ConversationService;
import com.chat.service.domain.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConversationUseCase {

    private final ConversationService conversationService;
    private final UserService userService;

    public ConversationUseCase(ConversationService conversationService, UserService userService) {
        this.conversationService = conversationService;
        this.userService = userService;
    }

    public Conversation createConversation(Conversation conversation, Set<Long> users) {
        if (conversationService.findById(conversation.conversationId()).isPresent()) {
            throw new BusinessError(String.format("Conversation with id %s has already exist", conversation.conversationId()));
        }
        Set<User> userList = users.stream()
                .map(id -> userService.findById(id)
                        .orElseThrow(() -> new BusinessError(String.format("User with id %s does not exist", id))))
                .collect(Collectors.toSet());
        return conversationService.save(conversation, userList);
    }

}
