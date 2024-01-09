package com.chat.service.domain.model;

public record Message(Long userId, Long conversationId, String text) {
}
