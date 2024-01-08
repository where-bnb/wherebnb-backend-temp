package com.wherebnb.wherebnb.domain.dao;

import com.wherebnb.wherebnb.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.apache.catalina.User;

@Getter
@Entity
public class Messages extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String content;

    @ManyToOne
    private Users sender;

    @ManyToOne
    private Users recipient;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "conversation_id")
    private Conversations conversations;

    @Enumerated
    private MessageStatus messageStatus;

    public Messages() {
    }

    @Builder
    public Messages(Long messageId, String content, Users sender, Users recipient, Conversations conversations, MessageStatus messageStatus) {
        this.messageId = messageId;
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.conversations = conversations;
        this.messageStatus = messageStatus;
    }

    // 연관관계 편의 메서드
    public void setSender(Users sender) {
        this.sender = sender;
        sender.addSentMessage(this);
    }

    public void setRecipient(Users recipient) {
        this.recipient = recipient;
        recipient.addReceivedMessage(this);
    }

    public void setConversations(Conversations conversations) {
        this.conversations = conversations;
        conversations.getMessages().add(this);
    }

}
