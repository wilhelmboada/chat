package com.chat.service.repository.converter;

import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;
import com.chat.service.repository.data.ConversationEntity;
import com.chat.service.repository.data.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserConverterTest {

    @Test
    void testToEntity_UserOnly() {

        long userId = 1L;
        String userName = "userName";
        User user = new User(userId, userName, Set.of());

        UserEntity entity = UserConverter.toEntity(user);

        assertNotNull(entity);
        assertEquals(userId, entity.getUserId());
        assertEquals(userName, entity.getUserName());
        assertTrue(entity.getConversations().isEmpty());
    }

    @Test
    void testToEntity_UserWithConversation() {

        long userId = 1L;
        String userName = "userName";
        Conversation conversation = new Conversation(2L, Set.of());
        User user = new User(userId, userName, Set.of());

        UserEntity entity = UserConverter.toEntity(user, conversation);

        assertNotNull(entity);
        assertEquals(userId, entity.getUserId());
        assertEquals(userName, entity.getUserName());
        assertEquals(1, entity.getConversations().size());
    }

    @Test
    void testToModel() {

        long userId = 1L;
        String userName = "userName";
        UserEntity userEntity = new UserEntity(userId, userName);
        userEntity.addConversation(new ConversationEntity(2L));

        User user = UserConverter.toModel(userEntity);

        assertNotNull(user);
        assertEquals(userId, user.userId());
        assertEquals(userName, user.userName());
        assertEquals(1, user.conversations().size());
    }
}
