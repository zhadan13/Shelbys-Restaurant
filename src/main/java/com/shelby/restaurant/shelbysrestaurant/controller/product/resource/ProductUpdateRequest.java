package com.shelby.restaurant.shelbysrestaurant.controller.product.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shelby.restaurant.shelbysrestaurant.model.product.Category;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProductUpdateRequest {

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "details", required = true)
    private String details;

    @JsonProperty(value = "ingredients", required = true)
    private Set<String> ingredients;

    @JsonProperty(value = "category", required = true)
    private Category category;

    @JsonProperty(value = "price", required = true)
    private Double price;

    @JsonProperty(value = "weight", required = true)
    private Double weight;

    @JsonProperty(value = "averageCookingTimeInMinutes", required = true)
    private Double averageCookingTimeInMinutes;

    @JsonProperty(value = "popularity", required = true)
    private Integer popularity;

    @JsonProperty(value = "isNew", required = true)
    private Boolean isNew;
}
