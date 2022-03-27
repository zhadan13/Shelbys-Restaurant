package com.shelby.restaurant.shelbysrestaurant.model.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String city;

    private String street;

    private String house;

    private String apartment;

    private String floor;

    private String entrance;
}
