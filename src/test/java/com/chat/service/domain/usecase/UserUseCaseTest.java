package com.chat.service.domain.usecase;

import com.chat.service.domain.error.BusinessError;
import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;
import com.chat.service.domain.service.ConversationService;
import com.chat.service.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private UserService userService;

    @Mock
    private ConversationService conversationService;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        long userId = 1L;
        User user = new User(userId, "userName", Set.of());

        when(userService.findById(userId)).thenReturn(Optional.empty());
        when(userService.save(user)).thenReturn(user);

        User createdUser = userUseCase.createUser(user);

        verify(userService).findById(userId);
        verify(userService).save(user);
        assertNotNull(createdUser);
        assertEquals(userId, createdUser.userId());
        assertEquals("userName", createdUser.userName());
    }

    @Test
    void testCreateUser_UserAlreadyExists() {

        long userId = 1L;
        User user = new User(userId, "userName", Set.of());

        when(userService.findById(userId)).thenReturn(Optional.of(user));

        assertThrows(BusinessError.class,
                () -> userUseCase.createUser(user));

    }

    @Test
    void testJoinConversation() {

        long userId = 1L;
        long conversationId = 2L;
        User user = new User(userId, "userName", Set.of());
        Conversation conversation = new Conversation(conversationId, Set.of());

        when(userService.findById(userId)).thenReturn(Optional.of(user));
        when(conversationService.findById(conversationId)).thenReturn(Optional.of(conversation));
        when(userService.joinConversation(user, conversation)).thenReturn(user);

        userUseCase.joinConversation(userId, conversationId);

        verify(userService).findById(userId);
        verify(conversationService).findById(conversationId);
        verify(userService).joinConversation(user, conversation);
    }

    @Test
    void testJoinConversation_UserNotFound() {

        long userId = 1L;
        long conversationId = 2L;

        when(userService.findById(userId)).thenReturn(Optional.empty());

        assertThrows(BusinessError.class,
                () -> userUseCase.joinConversation(userId, conversationId));
    }

    @Test
    void testJoinConversation_ConversationNotFound() {

        long userId = 1L;
        long conversationId = 2L;
        User user = new User(userId, "userName", Set.of());

        when(userService.findById(userId)).thenReturn(Optional.of(user));
        when(conversationService.findById(conversationId)).thenReturn(Optional.empty());

        assertThrows(BusinessError.class,
                () -> userUseCase.joinConversation(userId, conversationId));
    }

    @Test
    void testGetUser() {

        long userId = 1L;
        User user = new User(userId, "userName", Set.of());

        when(userService.findById(userId)).thenReturn(Optional.of(user));

        User retrievedUser = userUseCase.getUser(userId);

        verify(userService, times(1)).findById(userId);
        assertNotNull(retrievedUser);
        assertEquals(userId, retrievedUser.userId());
        assertEquals("userName", retrievedUser.userName());
    }

    @Test
    void testGetUser_UserNotFound() {

        long userId = 1L;

        when(userService.findById(userId)).thenReturn(Optional.empty());

        assertThrows(BusinessError.class,
                () -> userUseCase.getUser(userId));
    }
}

