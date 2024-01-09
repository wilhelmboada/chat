package com.chat.service.domain.model;

import java.util.Set;

public record User(Long userId, String userName, Set<Conversation> conversations) {
}
