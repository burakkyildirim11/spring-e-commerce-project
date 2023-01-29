package com.springCommerce.commerce.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateUserRequest {

  @NonNull private String firstName;
  @NonNull private String middleName;
  @NonNull private String lastName;
  @NonNull private String mail;
  @NonNull private Boolean isActive;
}
