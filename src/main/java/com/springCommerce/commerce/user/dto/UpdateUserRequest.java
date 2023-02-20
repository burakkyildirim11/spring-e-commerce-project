package com.springCommerce.commerce.user.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

  private String firstName;
  private String middleName;
  private String lastName;
}
