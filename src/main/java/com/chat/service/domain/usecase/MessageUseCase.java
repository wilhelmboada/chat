package com.chat.service.domain.usecase;

import com.chat.service.domain.error.BusinessError;
import com.chat.service.domain.model.Message;
import com.chat.service.domain.service.ConversationService;
import com.chat.service.domain.service.MessageService;
import com.chat.service.domain.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class MessageUseCase {

    private final MessageService messageService;
    private final UserService userService;
    private final ConversationService conversationService;

    public MessageUseCase(
            MessageService messageService,
            UserService userService,
            ConversationService conversationService) {
        this.messageService = messageService;
        this.userService = userService;
        this.conversationService = conversationService;
    }

    public void sendMessage(Message message) {
        userService.findById(message.userId())
                .map(user -> conversationService
                        .findById(message.conversationId())
                        .map(conversation -> messageService.save(message))
                        .orElseThrow(() -> new BusinessError(String.format("Conversation with id %s does not exist", message.conversationId()))))
                .orElseThrow(() -> new BusinessError(String.format("User with id %s does not exist", message.userId())));
    }
}
