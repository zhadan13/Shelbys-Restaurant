package com.shelby.restaurant.shelbysrestaurant.mapper;

import com.shelby.restaurant.shelbysrestaurant.controller.resource.UserUpdateRequest;
import com.shelby.restaurant.shelbysrestaurant.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "firstName", expression = "java(request.getFirstName())")
    @Mapping(target = "lastName", expression = "java(request.getLastName())")
    @Mapping(target = "phoneNumber", expression = "java(request.getPhoneNumber())")
    @Mapping(target = "address", expression = "java(request.getAddress())")
    void mapUserUpdateRequestToUser(UserUpdateRequest request, @MappingTarget User user);
}
