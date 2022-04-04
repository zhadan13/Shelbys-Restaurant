package com.shelby.restaurant.shelbysrestaurant.mapper.product;

import com.shelby.restaurant.shelbysrestaurant.controller.product.resource.ProductCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.controller.product.resource.ProductUpdateRequest;
import com.shelby.restaurant.shelbysrestaurant.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Component
@Validated
@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface ProductMapper {

    @Mapping(target = "name", expression = "java(request.getName())")
    @Mapping(target = "details", expression = "java(request.getDetails())")
    @Mapping(target = "ingredients", expression = "java(request.getIngredients())")
    @Mapping(target = "category", expression = "java(request.getCategory())")
    @Mapping(target = "price", expression = "java(request.getPrice())")
    @Mapping(target = "weight", expression = "java(request.getWeight())")
    @Mapping(target = "averageCookingTimeInMinutes", expression = "java(request.getAverageCookingTimeInMinutes())")
    @Mapping(target = "popularity", constant = "0")
    @Mapping(target = "isNew", constant = "true")
    @Mapping(target = "addedAt", expression = "java(LocalDateTime.now())")
    void mapProductCreateRequestToProduct(ProductCreateRequest request, @MappingTarget Product product);

    @Mapping(target = "name", expression = "java(request.getName())")
    @Mapping(target = "details", expression = "java(request.getDetails())")
    @Mapping(target = "ingredients", expression = "java(request.getIngredients())")
    @Mapping(target = "category", expression = "java(request.getCategory())")
    @Mapping(target = "price", expression = "java(request.getPrice())")
    @Mapping(target = "weight", expression = "java(request.getWeight())")
    @Mapping(target = "averageCookingTimeInMinutes", expression = "java(request.getAverageCookingTimeInMinutes())")
    @Mapping(target = "popularity", expression = "java(request.getPopularity())")
    @Mapping(target = "isNew", expression = "java(request.getIsNew())")
    void mapProductUpdateRequestToProduct(ProductUpdateRequest request, @MappingTarget Product product);
}
