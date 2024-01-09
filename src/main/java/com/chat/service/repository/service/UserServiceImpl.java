package com.chat.service.repository.service;

import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;
import com.chat.service.domain.service.UserService;
import com.chat.service.repository.UserRepository;
import com.chat.service.repository.converter.ConversationConverter;
import com.chat.service.repository.converter.UserConverter;
import com.chat.service.repository.data.ConversationEntity;
import com.chat.service.repository.data.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId).map(UserConverter::toModel);
    }

    public User save(User user) {
        UserEntity saved = userRepository.save(UserConverter.toEntity(user));
        return UserConverter.toModel(saved);
    }

    public User joinConversation(User user, Conversation conversation) {
        UserEntity saved = userRepository.save(UserConverter.toEntity(user, conversation));
        return UserConverter.toModel(saved);
    }
}
