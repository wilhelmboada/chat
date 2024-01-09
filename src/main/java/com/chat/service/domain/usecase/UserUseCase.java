package com.chat.service.domain.usecase;

import com.chat.service.domain.error.BusinessError;
import com.chat.service.domain.model.User;
import com.chat.service.domain.service.ConversationService;
import com.chat.service.domain.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserUseCase {

    private final UserService userService;
    private final ConversationService conversationService;

    public UserUseCase(UserService userService, ConversationService conversationService) {
        this.userService = userService;
        this.conversationService = conversationService;
    }

    public User createUser(User user) {
        if (userService.findById(user.userId()).isPresent()) {
            throw new BusinessError(String.format("User with id %s has already exist", user.userId()));
        }
        return userService.save(user);
    }

    public void joinConversation(Long userId, Long conversationId) {
        userService.findById(userId)
                .map(user -> conversationService
                        .findById(conversationId)
                        .map(conversation -> userService.joinConversation(user, conversation))
                        .orElseThrow(() -> new BusinessError(String.format("Conversation with id %s does not exist", conversationId))))
                .orElseThrow(() -> new BusinessError(String.format("User with id %s does not exist", userId)));
    }

    public User getUser(Long userId) {
        return userService.findById(userId)
                .orElseThrow(() -> new BusinessError(String.format("User with id %s does not exist", userId)));
    }

}
