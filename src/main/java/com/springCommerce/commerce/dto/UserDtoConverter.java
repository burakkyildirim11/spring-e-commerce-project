package com.springCommerce.commerce.dto;

import com.springCommerce.commerce.model.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

  private final UserDetailsDtoConverter converter;

  public UserDtoConverter(UserDetailsDtoConverter converter) {
    this.converter = converter;
  }

  public UserDto convert(Users user) {
    return new UserDto(
<<<<<<< HEAD
            user.getFirstName(),
            user.getMiddleName(),
            user.getLastName(),
            user.getMail(),
            converter.convert(new ArrayList<>(user.getUserDetailsSet())));
=======
        user.getFirstName(),
        user.getMiddleName(),
        user.getLastName(),
        user.getMail(),
        converter.convert(new ArrayList<>(user.getUserDetailsSet())));
>>>>>>> e0ccc1d1a10e3cc3ce19b2021b7aaa80e32cefc2
  }

  public List<UserDto> convert(List<Users> userList) {
    return userList.stream()
<<<<<<< HEAD
            .map(
                    user ->
                            new UserDto(
                                    user.getFirstName(),
                                    user.getMiddleName(),
                                    user.getLastName(),
                                    user.getMail(),
                                    converter.convert(new ArrayList<>(user.getUserDetailsSet()))))
            .collect(Collectors.toList());
=======
        .map(
            user ->
                new UserDto(
                    user.getFirstName(),
                    user.getMiddleName(),
                    user.getLastName(),
                    user.getMail(),
                    converter.convert(new ArrayList<>(user.getUserDetailsSet()))))
        .collect(Collectors.toList());
>>>>>>> e0ccc1d1a10e3cc3ce19b2021b7aaa80e32cefc2
  }
}