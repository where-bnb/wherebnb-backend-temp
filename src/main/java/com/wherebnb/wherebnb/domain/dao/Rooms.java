package com.wherebnb.wherebnb.domain.dao;

import com.wherebnb.wherebnb.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
public class Rooms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(nullable = false)
    private String roomMessage;

    @Embedded
    private Address address;

    private int charge;

    @ElementCollection // 테이블을 따로 만드는 것이 더 효율적
    private List<String> roomPhotos;

    @ElementCollection
    private List<String> amenities;

    private boolean guestFavorite;

    @ElementCollection
    private List<String> languages;

    @Embedded
    private Info info;

    @ElementCollection
    private Set<String> tags = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id") //현업에서 joinColumn 은 거의 안씀 -> db연관관계를 아예 안만들어버림 (논리적으로만 생각)
    private Users users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rooms")
//    @JoinColumn(name = "booking_id")
    private List<Bookings> bookings = new ArrayList<>();


    public Rooms() {
    }

    @Builder
    public Rooms(Long roomId, String roomMessage, Address address, int charge, List<String> roomPhotos, List<String> amenities, boolean guestFavorite, List<String> languages, Info info, Set<String> tags, Users users, List<Bookings> bookings) {
        this.roomId = roomId;
        this.roomMessage = roomMessage;
        this.address = address;
        this.charge = charge;
        this.roomPhotos = roomPhotos;
        this.amenities = amenities;
        this.guestFavorite = guestFavorite;
        this.languages = languages;
        this.info = info;
        this.tags = tags;
        this.users = users;
        this.bookings = bookings;
    }

    //== 연관관계 메서드 ==//
    public void setUsers(Users users) {
        this.users = users;
        users.getRooms().add(this);
    }

    public void setBookings(Bookings bookings) {
        this.bookings.add(bookings);
        bookings.setRooms(this);
    }

}
