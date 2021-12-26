package com.shelby.restaurant.shelbysrestaurant.model.address;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

    private String city;

    private String street;

    private String house;

    private String apartment;

    private String floor;

    private String entrance;
}
