package com.wherebnb.wherebnb.domain.dao;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String country;
    private String state;
    private String city;
    private String street;
    private String details;
    private String zipcode;
    private Double latitude;
    private Double longitude;

    public Address() {
    }

    @Builder
    public Address(String country, String state, String city, String street, String details, String zipcode, Double latitude, Double longitude) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.details = details;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
