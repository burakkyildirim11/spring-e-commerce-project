package com.springCommerce.commerce.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDetailsRequest {

  private String phoneNumber;
  private String city;
  private String country;
  private String postCode;
  private String address;
  private Long userId;

}
