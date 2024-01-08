package com.wherebnb.wherebnb.domain.dao;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
public class Info {

    private int capacity;
    private int bedRooms;
    private int beds;
    private int bathRooms;

    public Info() {
    }

    @Builder
    public Info(int capacity, int bedRooms, int beds, int bathRooms) {
        this.capacity = capacity;
        this.bedRooms = bedRooms;
        this.beds = beds;
        this.bathRooms = bathRooms;
    }
}
