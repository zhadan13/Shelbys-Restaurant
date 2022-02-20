package com.shelby.restaurant.shelbysrestaurant.controller.user.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shelby.restaurant.shelbysrestaurant.model.address.Address;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserUpdateRequest {

    @JsonProperty(value = "firstName")
    @Size(min = 1)
    private String firstName;

    @JsonProperty(value = "lastName")
    @Size(min = 1)
    private String lastName;

    @JsonProperty(value = "phoneNumber")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")
    private String phoneNumber;

    @JsonProperty(value = "address")
    private Address address;
}
