package com.shelby.restaurant.shelbysrestaurant.controller.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shelby.restaurant.shelbysrestaurant.model.address.Address;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserCreateRequest {

    @JsonProperty(value = "email", required = true)
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    private String email;

    @JsonProperty(value = "phoneNumber", required = true)
    @NotNull
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")
    private String phoneNumber;

    @JsonProperty(value = "password", required = true)
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
    private String password;

    @JsonProperty(value = "firstName")
    @Size(min = 1)
    private String firstName;

    @JsonProperty(value = "lastName")
    @Size(min = 1)
    private String lastName;

    @JsonProperty(value = "address")
    private Address address;
}
