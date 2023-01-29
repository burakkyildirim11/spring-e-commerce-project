package com.springCommerce.commerce;

import com.springCommerce.commerce.dto.UserDto;
import com.springCommerce.commerce.model.User;

import java.util.List;
import java.util.Random;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

  private static Long userId = 100L;

  public static List<User> generateUsers() {

    return IntStream.range(0, 5)
        .mapToObj(
            i ->
                new User(
                    ((long) i),
                    i + "firstName",
                    i + "middleName",
                    i + "lastName",
                    i + "@mail.com",
                    new Random(2).nextBoolean()))
        .collect(Collectors.toList());
  }

  public static List<UserDto> generateUserDtoList(List<User> userList) {
    return userList.stream()
        .map(
            from ->
                new UserDto(
                    from.getFirstName(), from.getMiddleName(), from.getLastName(), from.getMail()))
        .collect(Collectors.toList());
  }

  public static User generateUser(String mail) {
    return new User(
        ((long) userId),
        userId + "firstName",
        userId + "middleName",
        userId + "lastName",
        mail,
        true);
  }

  public static UserDto generateUserDto(String mail) {
    return new UserDto("firstName" + userId, "middleName" + userId, "lastName" + userId, mail);
  }
}
