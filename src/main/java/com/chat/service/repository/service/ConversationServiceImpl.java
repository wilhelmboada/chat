package com.chat.service.repository.service;

import com.chat.service.domain.model.Conversation;
import com.chat.service.domain.model.User;
import com.chat.service.domain.service.ConversationService;
import com.chat.service.repository.ConversationRepository;
import com.chat.service.repository.converter.ConversationConverter;
import com.chat.service.repository.data.ConversationEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

    ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public Optional<Conversation> findById(Long conversationId) {
        return conversationRepository.findById(conversationId).map(ConversationConverter::toModel);
    }

    public Conversation save(Conversation conversation, Set<User> users) {
        ConversationEntity saved = conversationRepository.save(ConversationConverter.toEntity(conversation, users));
        return ConversationConverter.toModel(saved);
    }
}
