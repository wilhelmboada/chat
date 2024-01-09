package com.chat.service.repository.data;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @ManyToMany
    @JoinTable(
            name = "users_conversations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conversation_id"))
    Set<ConversationEntity> conversations = new HashSet<>();

    public UserEntity(Long userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }

    public UserEntity() {

    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Set<ConversationEntity> getConversations() {
        return conversations;
    }

    public void addConversation(ConversationEntity conversationEntity) {
        this.conversations.add(conversationEntity);
    }
}

