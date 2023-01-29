package com.springCommerce.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private String firstName;
  private String middleName;
  private String lastName;
  private String mail;
}
