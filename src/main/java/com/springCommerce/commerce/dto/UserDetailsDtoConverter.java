package com.springCommerce.commerce.dto;

import com.springCommerce.commerce.model.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsDtoConverter {

  public UserDetailsDto convert(UserDetails userDetails) {
    return new UserDetailsDto(
        userDetails.getPhoneNumber(),
        userDetails.getAddress(),
        userDetails.getCity(),
        userDetails.getCountry(),
        userDetails.getPostCode());
  }

  public List<UserDetailsDto> convert(List<UserDetails> userDetailsList) {
    return userDetailsList.stream()
        .map(
            userDetails ->
                new UserDetailsDto(
                    userDetails.getPhoneNumber(),
                    userDetails.getAddress(),
                    userDetails.getCity(),
                    userDetails.getCountry(),
                    userDetails.getPostCode()))
        .collect(Collectors.toList());
  }
}
