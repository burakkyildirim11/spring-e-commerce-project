package com.springCommerce.commerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private String mail;

}
