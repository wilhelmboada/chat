package com.chat.service.repository.data;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conversations")
public class ConversationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id")
    private Long conversationId;

    @ManyToMany
    @JoinTable(
            name = "users_conversations",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<UserEntity> conversationUsers = new HashSet<>();

    public ConversationEntity(Long conversationId) {
        this.conversationId = conversationId;
    }

    public ConversationEntity() {

    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public Set<UserEntity> getConversationUsers() {
        return conversationUsers;
    }

    public void addUsers(UserEntity userEntity) {
        this.conversationUsers.add(userEntity);
    }
}
