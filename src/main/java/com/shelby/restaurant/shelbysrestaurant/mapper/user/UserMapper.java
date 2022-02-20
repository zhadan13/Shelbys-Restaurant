package com.shelby.restaurant.shelbysrestaurant.mapper.user;

import com.shelby.restaurant.shelbysrestaurant.controller.user.resource.UserCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.controller.user.resource.UserUpdateRequest;
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

    @Mapping(target = "email", expression = "java(request.getEmail())")
    @Mapping(target = "phoneNumber", expression = "java(request.getPhoneNumber())")
    @Mapping(target = "password", expression = "java(request.getPassword())")
    @Mapping(target = "firstName", expression = "java(request.getFirstName())")
    @Mapping(target = "lastName", expression = "java(request.getLastName())")
    @Mapping(target = "role", expression = "java(request.getRole())")
    @Mapping(target = "address", expression = "java(request.getAddress())")
    void mapUserCreateRequestToUser(UserCreateRequest request, @MappingTarget User user);
}
