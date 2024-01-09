package com.chat.service.domain.service;

import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;

import java.util.Optional;
import java.util.Set;


public interface ConversationService {

    Optional<Conversation> findById(Long conversationId);

    Conversation save(Conversation conversation, Set<User> users);
}
