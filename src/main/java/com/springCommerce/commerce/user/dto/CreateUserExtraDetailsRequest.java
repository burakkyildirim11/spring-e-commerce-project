package com.springCommerce.commerce.user.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserExtraDetailsRequest {

  private String phoneNumber;
  private String city;
  private String country;
  private String postCode;
  private String address;
  private Long userId;

}
