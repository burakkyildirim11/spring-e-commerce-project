package com.springCommerce.commerce.user.dto;

import com.springCommerce.commerce.user.model.UserExtraDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserExtraDetailsDtoConverter {

  public UserExtraDetailsDto convert(UserExtraDetails userExtraDetails) {
    return new UserExtraDetailsDto(
        userExtraDetails.getPhoneNumber(),
        userExtraDetails.getAddress(),
        userExtraDetails.getCity(),
        userExtraDetails.getCountry(),
        userExtraDetails.getPostCode());
  }

  public List<UserExtraDetailsDto> convert(List<UserExtraDetails> userExtraDetailsList) {
    return userExtraDetailsList.stream()
        .map(
            userDetails ->
                new UserExtraDetailsDto(
                    userDetails.getPhoneNumber(),
                    userDetails.getAddress(),
                    userDetails.getCity(),
                    userDetails.getCountry(),
                    userDetails.getPostCode()))
        .collect(Collectors.toList());
  }
}
