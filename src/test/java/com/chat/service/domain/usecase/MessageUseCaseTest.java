package com.chat.service.domain.usecase;

import com.chat.service.domain.error.BusinessError;
import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.Message;
import com.chat.service.domain.model.User;
import com.chat.service.domain.service.ConversationService;
import com.chat.service.domain.service.MessageService;
import com.chat.service.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MessageUseCaseTest {

    @Mock
    private MessageService messageService;

    @Mock
    private UserService userService;

    @Mock
    private ConversationService conversationService;

    @InjectMocks
    private MessageUseCase messageUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {

        long userId = 1L;
        long conversationId = 2L;
        Message message = new Message(userId, conversationId, "text");
        Conversation conversation = new Conversation(conversationId, Set.of());
        User user = new User(userId, "userName", Set.of(conversation));

        when(userService.findById(userId)).thenReturn(Optional.of(user));
        when(conversationService.findById(conversationId)).thenReturn(Optional.of(conversation));
        when(messageService.save(message)).thenReturn(message);

        messageUseCase.sendMessage(message);

        verify(userService, times(1)).findById(userId);
        verify(conversationService, times(1)).findById(conversationId);
        verify(messageService, times(1)).save(message);
    }

    @Test
    void testSendMessage_UserNotFound() {

        long userId = 1L;
        long conversationId = 2L;
        Message message = new Message(userId, conversationId, "Hello, World!");

        when(userService.findById(userId)).thenReturn(Optional.empty());

        assertThrows(BusinessError.class,
                () -> messageUseCase.sendMessage(message));
    }

    @Test
    void testSendMessage_ConversationNotFound() {

        long userId = 1L;
        long conversationId = 2L;
        Message message = new Message(userId, conversationId, "text");
        Conversation conversation = new Conversation(conversationId, Set.of());
        User user = new User(userId, "userName", Set.of(conversation));

        when(userService.findById(userId)).thenReturn(Optional.of(user));
        when(conversationService.findById(conversationId)).thenReturn(Optional.empty());

        assertThrows(BusinessError.class,
                () -> messageUseCase.sendMessage(message));;
    }
}

