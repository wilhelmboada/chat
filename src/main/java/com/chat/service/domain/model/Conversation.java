package com.chat.service.domain.model;

import java.util.Set;

public record Conversation(Long conversationId, Set<User> users) {
}
