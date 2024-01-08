package com.wherebnb.wherebnb.domain.dao;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private int cleanScore;

    private int communicationScore;
    private int checkInScore;
    private int accuracyScore;
    private int locationScore;
    private int priceScore;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private LocalDateTime reviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    private Users users;

    public Reviews() {
    }

    @Builder
    public Reviews(Long reviewId, int cleanScore, int communicationScore, int checkInScore, int accuracyScore, int locationScore, int priceScore, String content, LocalDateTime reviewDate, Users users) {
        this.reviewId = reviewId;
        this.cleanScore = cleanScore;
        this.communicationScore = communicationScore;
        this.checkInScore = checkInScore;
        this.accuracyScore = accuracyScore;
        this.locationScore = locationScore;
        this.priceScore = priceScore;
        this.content = content;
        this.reviewDate = reviewDate;
        this.users = users;
    }

    //== 연관관계 메서드 ==//
    public void setUsers(Users users) {
        this.users = users;
        users.getReviews().add(this);
    }
}
