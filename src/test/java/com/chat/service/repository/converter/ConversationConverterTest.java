package com.chat.service.repository.converter;

import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;
import com.chat.service.repository.data.ConversationEntity;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ConversationConverterTest {

    @Test
    void testToEntity_ConversationOnly() {

        long conversationId = 1L;
        Conversation conversation = new Conversation(conversationId, Set.of());

        ConversationEntity entity = ConversationConverter.toEntity(conversation);

        assertNotNull(entity);
        assertEquals(conversationId, entity.getConversationId());
        assertTrue(entity.getConversationUsers().isEmpty());
    }

    @Test
    void testToEntity_ConversationWithUsers() {

        long conversationId = 1L;
        Set<User> users = new HashSet<>();
        users.add(new User(1L, "User1", Set.of()));
        users.add(new User(2L, "User2", Set.of()));
        Conversation conversation = new Conversation(conversationId, users);

        ConversationEntity entity = ConversationConverter.toEntity(conversation, users);

        assertNotNull(entity);
        assertEquals(conversationId, entity.getConversationId());
        assertEquals(users.size(), entity.getConversationUsers().size());
        assertTrue(entity.getConversationUsers().stream().allMatch(userEntity -> users.contains(UserConverter.toModel(userEntity))));
    }

    @Test
    void testToModel() {

        long conversationId = 1L;
        ConversationEntity entity = new ConversationEntity(conversationId);

        Conversation conversation = ConversationConverter.toModel(entity);

        assertNotNull(conversation);
        assertEquals(conversationId, conversation.conversationId());
        assertTrue(conversation.users().isEmpty());
    }
}

