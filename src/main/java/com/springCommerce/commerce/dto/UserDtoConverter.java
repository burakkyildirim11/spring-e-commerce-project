package com.springCommerce.commerce.dto;

import com.springCommerce.commerce.model.Users;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

  public UserDto convert(Users user) {
    return new UserDto(
        user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getMail());
  }

  public List<UserDto> convert(List<Users> userList) {
    return userList.stream()
        .map(
            user ->
                new UserDto(
                    user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getMail()))
        .collect(Collectors.toList());
  }
}
