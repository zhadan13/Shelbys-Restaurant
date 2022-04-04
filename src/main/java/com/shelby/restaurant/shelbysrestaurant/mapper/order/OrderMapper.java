package com.shelby.restaurant.shelbysrestaurant.mapper.order;

import com.shelby.restaurant.shelbysrestaurant.controller.order.resource.OrderCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.model.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Component
@Validated
@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface OrderMapper {

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    void mapOrderCreateRequestToOrder(OrderCreateRequest request, @MappingTarget Order order);
}
