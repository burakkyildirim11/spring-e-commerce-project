package com.springCommerce.commerce.dto;

import com.springCommerce.commerce.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

  public UserDto convert(User user) {
    return new UserDto(
        user.getFirstName(),
        user.getMiddleName(),
        user.getLastName(),
        user.getMail(),
        user.getIsActive());
  }
}
