package com.wherebnb.wherebnb.domain.dao;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Conversations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conversationId;

    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conversations")//  유저 1-N 대화 방 1-N 대화 -> 최대한 다대다 관계 피하기
//    @JoinColumn(name = "user_id")
    private List<UserConversations> userConversations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conversations")
    private List<Messages> messages = new ArrayList<>();

    public Conversations() {
    }

    @Builder
    public Conversations(Long conversationId, String title, List<UserConversations> userConversations, List<Messages> messages) {
        this.conversationId = conversationId;
        this.title = title;
        this.userConversations = userConversations;
        this.messages = messages;
    }

    //==연관관계 메서드==//
    public void setUserConversations(UserConversations userConversations) {
        this.userConversations.add(userConversations);
        userConversations.setConversations(this);
    }
    public void serMessages(Messages messages) {
        this.messages.add(messages);
        messages.setConversations(this);
    }

}
