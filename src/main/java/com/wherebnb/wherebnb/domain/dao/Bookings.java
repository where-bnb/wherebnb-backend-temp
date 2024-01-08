package com.wherebnb.wherebnb.domain.dao;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private int totalPrice;

    @Enumerated
    private BookingStatus bookingStatus;

    private LocalDateTime BookingDate;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "room_id")
    private Rooms rooms;

    @OneToOne(fetch = FetchType.LAZY)
    private Payments payments;

    public Bookings() {
    }

    @Builder
    public Bookings(Long bookingId, LocalDateTime checkInDate, LocalDateTime checkOutDate, int totalPrice, BookingStatus bookingStatus, LocalDateTime bookingDate, Rooms rooms, Payments payments) {
        this.bookingId = bookingId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.bookingStatus = bookingStatus;
        BookingDate = bookingDate;
        this.rooms = rooms;
        this.payments = payments;
    }

    //== 연관관계 메서드 ==//
    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
        rooms.getBookings().add(this);
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
        if (payments.getBookings() != this) {
            payments.setBookings(this);
        }
    }
}
