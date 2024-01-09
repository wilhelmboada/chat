package com.chat.service.repository.service;

import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;
import com.chat.service.repository.UserRepository;
import com.chat.service.repository.data.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById_UserExists() {

        long userId = 1L;
        UserEntity userEntity = new UserEntity(userId, "userName");
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        Optional<User> result = userService.findById(userId);

        verify(userRepository).findById(userId);
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().userId());
        assertEquals("userName", result.get().userName());
    }

    @Test
    void testFindById_UserDoesNotExist() {

        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> result = userService.findById(userId);

        verify(userRepository, times(1)).findById(userId);
        assertFalse(result.isPresent());
    }

    @Test
    void testSaveUser() {

        User user = new User(1L, "userName", Set.of());
        UserEntity savedEntity = new UserEntity(user.userId(), user.userName());
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedEntity);

        User savedUser = userService.save(user);

        verify(userRepository).save(any(UserEntity.class));
        assertNotNull(savedUser);
        assertEquals(user.userId(), savedUser.userId());
        assertEquals(user.userName(), savedUser.userName());
    }

    @Test
    void testJoinConversation() {

        User user = new User(1L, "userName", Set.of());
        Conversation conversation = new Conversation(2L, Set.of());
        UserEntity savedEntity = new UserEntity(user.userId(), user.userName());
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedEntity);

        User updatedUser = userService.joinConversation(user, conversation);

        verify(userRepository, times(1)).save(any(UserEntity.class));
        assertNotNull(updatedUser);
        assertEquals(user.userId(), updatedUser.userId());
        assertEquals(user.userName(), updatedUser.userName());
    }
}

