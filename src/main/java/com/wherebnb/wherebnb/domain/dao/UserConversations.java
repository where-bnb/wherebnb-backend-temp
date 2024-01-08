package com.wherebnb.wherebnb.domain.dao;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class UserConversations {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userConversationsId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    private Conversations conversations;

    @Builder
    public UserConversations(Long userConversationsId, Users users, Conversations conversations) {
        this.userConversationsId = userConversationsId;
        this.users = users;
        this.conversations = conversations;
    }

    public UserConversations() {
    }

    //==연관관계 편의 메서드 ==//
    public void setUsers(Users users) {
        this.users = users;
        users.getUserConversations().add(this);
    }

    public void setConversations (Conversations conversations) {
        this.conversations = conversations;
        conversations.getUserConversations().add(this);
    }
}
