package com.chat.service.domain.service;

import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long userId);

    User save(User user);

    public User joinConversation(User user, Conversation conversation);
}
