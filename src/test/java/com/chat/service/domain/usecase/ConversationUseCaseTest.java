package com.chat.service.domain.usecase;

import com.chat.service.domain.error.BusinessError;
import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;
import com.chat.service.domain.service.ConversationService;
import com.chat.service.domain.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ConversationUseCaseTest {

    @Mock
    private ConversationService conversationService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ConversationUseCase conversationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateConversation() {

        long conversationId = 1L;
        Set<User> users = Set.of();
        Conversation conversation = new Conversation(conversationId, users);

        when(conversationService.findById(conversationId)).thenReturn(Optional.empty());
        when(conversationService.save(conversation, users)).thenReturn(conversation);

        Conversation createdConversation = conversationUseCase.createConversation(conversation, Set.of());

        verify(conversationService, times(1)).findById(conversationId);
        verify(userService, never()).findById(anyLong());
        verify(conversationService).save(conversation, users);
        Assertions.assertNotNull(createdConversation);
        assertEquals(conversationId, createdConversation.conversationId());
        assertEquals(0, createdConversation.users().size());
    }

    @Test
    void testCreateConversation_ConversationAlreadyExists() {

        long conversationId = 1L;
        Set<User> users = Set.of();
        Conversation conversation = new Conversation(conversationId, users);

        when(conversationService.findById(conversationId)).thenReturn(Optional.of(new Conversation(conversationId, Set.of())));

        assertThrows(BusinessError.class,
                () -> conversationUseCase.createConversation(conversation, Set.of()));
    }

    @Test
    void testCreateConversation_UserNotFound() {
        long conversationId = 1L;
        Set<User> users = Set.of();
        Conversation conversation = new Conversation(conversationId, users);
        Set<Long> userIds = new HashSet<>(Collections.singletonList(2L));

        when(conversationService.findById(conversationId)).thenReturn(Optional.empty());

        when(userService.findById(2L)).thenReturn(Optional.empty());

        assertThrows(BusinessError.class,
                () -> conversationUseCase.createConversation(conversation, userIds));

    }
}

