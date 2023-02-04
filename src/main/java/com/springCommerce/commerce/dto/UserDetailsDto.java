package com.springCommerce.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {

  private String phoneNumber;
  private String address;
  private String city;
  private String country;
  private String postCode;

}
