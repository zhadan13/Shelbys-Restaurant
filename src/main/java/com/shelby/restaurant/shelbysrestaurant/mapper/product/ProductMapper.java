package com.shelby.restaurant.shelbysrestaurant.mapper.product;

import com.shelby.restaurant.shelbysrestaurant.controller.product.resource.ProductCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.controller.product.resource.ProductUpdateRequest;
import com.shelby.restaurant.shelbysrestaurant.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "name", expression = "java(request.getName())")
    @Mapping(target = "details", expression = "java(request.getDetails())")
    @Mapping(target = "category", expression = "java(request.getCategory())")
    @Mapping(target = "price", expression = "java(request.getPrice())")
    @Mapping(target = "weight", expression = "java(request.getWeight())")
    void mapProductCreateRequestToProduct(ProductCreateRequest request, @MappingTarget Product product);

    @Mapping(target = "name", expression = "java(request.getName())")
    @Mapping(target = "details", expression = "java(request.getDetails())")
    @Mapping(target = "category", expression = "java(request.getCategory())")
    @Mapping(target = "price", expression = "java(request.getPrice())")
    @Mapping(target = "weight", expression = "java(request.getWeight())")
    @Mapping(target = "popularity", expression = "java(request.getPopularity())")
    @Mapping(target = "isNew", expression = "java(request.getIsNew())")
    void mapProductUpdateRequestToProduct(ProductUpdateRequest request, @MappingTarget Product product);
}
