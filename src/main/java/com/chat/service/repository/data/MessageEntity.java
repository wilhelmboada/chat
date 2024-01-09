package com.chat.service.repository.data;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "conversation_id", nullable = false)
    private Long conversationId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "text", nullable = false)
    private String text;

    public MessageEntity() {
    }

    public MessageEntity(Long userId, Long conversationId, String text) {
        this.userId = userId;
        this.conversationId = conversationId;
        this.text = text;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }
}
