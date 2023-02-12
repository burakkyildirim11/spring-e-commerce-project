package com.springCommerce.commerce.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

  @NonNull
  private String firstName;
  @NonNull
  private String middleName;
  @NonNull
  private String lastName;
  @NonNull
  private String mail;

  private List<UserDetailsDto> userDetailsDto;
}