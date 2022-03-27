package com.shelby.restaurant.shelbysrestaurant.controller.product.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shelby.restaurant.shelbysrestaurant.model.product.Category;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
public class ProductCreateRequest {

    @JsonProperty(value = "name", required = true)
    @NotNull
    private String name;

    @JsonProperty(value = "details", required = true)
    private String details;

    @JsonProperty(value = "ingredients", required = true)
    private Set<String> ingredients;

    @JsonProperty(value = "category", required = true)
    @NotNull
    private Category category;

    @JsonProperty(value = "price", required = true)
    @NotNull
    private Double price;

    @JsonProperty(value = "weight", required = true)
    @NotNull
    private Double weight;
}
