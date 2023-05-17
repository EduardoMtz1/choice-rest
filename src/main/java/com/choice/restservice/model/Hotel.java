package com.choice.restservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    private long id;
    private String name;
    private String address;
    private int rating;

    public Hotel(String name, String address, Integer rating) {
        this.name = name;
        this.address = address;
        this.rating = rating;
    }
}
