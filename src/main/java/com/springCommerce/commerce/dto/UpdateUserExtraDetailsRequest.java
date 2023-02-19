package com.springCommerce.commerce.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserExtraDetailsRequest {

  private String phoneNumber;
  private String address;
  private String city;
  private String country;
  private String postCode;
}
