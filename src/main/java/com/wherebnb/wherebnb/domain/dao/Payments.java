package com.wherebnb.wherebnb.domain.dao;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payId;

    private int amount;
    @Enumerated
    private PaymentMethod paymentMethod;

    @Enumerated
    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDate;
    private String transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @OneToOne(fetch = FetchType.LAZY)
    private Bookings bookings;

    public Payments() {
    }

    @Builder
    public Payments(Long payId, int amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus, LocalDateTime paymentDate, String transactionId, Users users, Bookings bookings) {
        this.payId = payId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.transactionId = transactionId;
        this.users = users;
        this.bookings = bookings;
    }

    //== 연관관계 메서드 ==//
    public void setUsers(Users users) {
        this.users = users;
        users.getPayments().add(this);
    }

    public void setBookings(Bookings bookings) {
        this.bookings = bookings;
        if (bookings.getPayments() != this) {
            bookings.setPayments(this);
        }
    }

}
