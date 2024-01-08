package com.wherebnb.wherebnb.domain.dao;

import com.wherebnb.wherebnb.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String picture;

    private String password;

    private String phoneNumber;

    private String emergencyNumber;

    @Embedded
    private Address address;

    @OneToMany(fetch = LAZY, mappedBy = "users") //many쪽에 연관관계 주인설정
//    @JoinColumn(name = "room_id")
    private List<Rooms> rooms = new ArrayList<>();

    @OneToMany(fetch = LAZY, mappedBy = "users")
//    @JoinColumn(name = "review_id")
    private List<Reviews> reviews = new ArrayList<>();

    @OneToMany(fetch = LAZY, mappedBy = "users")
//    @JoinColumn(name = "pay_id")
    private List<Payments> payments = new ArrayList<>();

    @OneToMany(fetch = LAZY, mappedBy = "users")
//    @JoinColumn(name = "conversation_id")
    private List<UserConversations> userConversations = new ArrayList<>();

    @OneToMany(fetch = LAZY, mappedBy = "sender")
    private List<Messages> sentMessages;

    @OneToMany(fetch = LAZY, mappedBy = "recipient")
    private List<Messages> receivedMessages;


    public Users() {
    }

    @Builder
    public Users(Long userId, String name, String email, String picture, String password, String phoneNumber, String emergencyNumber, Address address) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.emergencyNumber = emergencyNumber;
        this.address = address;
    }

    //== 연관관계 메서드 ==//
    public void setRooms(Rooms rooms) {
        this.rooms.add(rooms);
        rooms.setUsers(this);
    }

    public void setReviews(Reviews reviews) {
        this.reviews.add(reviews);
        reviews.setUsers(this);
    }

    public void setPayments(Payments payments) {
        this.payments.add(payments);
        payments.setUsers(this);
    }

    public void setUserConversations(UserConversations userConversations) {
        this.userConversations.add(userConversations);
        userConversations.setUsers(this);
    }

    public void addSentMessage(Messages message) {
        sentMessages.add(message);
        if (message.getSender() != this) {
            message.setSender(this);
        }
    }

    public void addReceivedMessage(Messages message) {
        receivedMessages.add(message);
        if (message.getRecipient() != this) {
            message.setRecipient(this);
        }
    }

}
