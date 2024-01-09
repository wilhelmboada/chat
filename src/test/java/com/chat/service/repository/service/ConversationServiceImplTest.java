package com.chat.service.repository.service;

import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;
import com.chat.service.repository.ConversationRepository;
import com.chat.service.repository.data.ConversationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConversationServiceImplTest {

    @Mock
    private ConversationRepository conversationRepository;

    @InjectMocks
    private ConversationServiceImpl conversationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById_ConversationExists() {

        long conversationId = 1L;
        ConversationEntity conversationEntity = new ConversationEntity(conversationId);
        when(conversationRepository.findById(conversationId)).thenReturn(Optional.of(conversationEntity));

        Optional<Conversation> result = conversationService.findById(conversationId);

        verify(conversationRepository, times(1)).findById(conversationId);
        assertTrue(result.isPresent());
        assertEquals(conversationId, result.get().conversationId());
    }

    @Test
    void testFindById_ConversationDoesNotExist() {

        long conversationId = 1L;

        when(conversationRepository.findById(conversationId)).thenReturn(Optional.empty());

        Optional<Conversation> result = conversationService.findById(conversationId);

        verify(conversationRepository).findById(conversationId);
        assertFalse(result.isPresent());
    }

    @Test
    void testSaveConversation() {

        long conversationId = 1L;
        Set<User> users = Set.of(new User(1L, "User1", Set.of()), new User(2L, "User2", Set.of()));
        Conversation conversation = new Conversation(conversationId, Set.of());
        ConversationEntity savedEntity = new ConversationEntity(conversationId);

        when(conversationRepository.save(any(ConversationEntity.class))).thenReturn(savedEntity);

        Conversation savedConversation = conversationService.save(conversation, users);

        verify(conversationRepository).save(any(ConversationEntity.class));
        assertNotNull(savedConversation);
        assertEquals(conversationId, savedConversation.conversationId());
    }

}

