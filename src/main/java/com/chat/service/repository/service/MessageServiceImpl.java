package com.chat.service.repository.service;

import com.chat.service.domain.model.Message;
import com.chat.service.domain.service.MessageService;
import com.chat.service.repository.MessageRepository;
import com.chat.service.repository.converter.MessageConverter;
import com.chat.service.repository.data.MessageEntity;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message message) {
        MessageEntity saved = messageRepository.save(MessageConverter.toEntity(message));
        return MessageConverter.toModel(saved);
    }
}
