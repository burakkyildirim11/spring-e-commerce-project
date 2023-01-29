package com.springCommerce.commerce.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

  private String firstName;
  private String middleName;
  private String lastName;
  private String mail;
}
