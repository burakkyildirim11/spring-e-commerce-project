package com.springCommerce.commerce.dto;

import com.springCommerce.commerce.model.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    private final UserExtraDetailsDtoConverter converter;

    public UserDtoConverter(UserExtraDetailsDtoConverter converter) {
        this.converter = converter;
    }

    public UserDto convert(Users user) {
        return new UserDto(
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getMail(),
                converter.convert(new ArrayList<>(user.getUserExtraDetailsSet())));
    }

    public List<UserDto> convert(List<Users> userList) {
        return userList.stream()
                .map(
                        user ->
                                new UserDto(
                                        user.getFirstName(),
                                        user.getMiddleName(),
                                        user.getLastName(),
                                        user.getMail(),
                                        converter.convert(new ArrayList<>(user.getUserExtraDetailsSet()))))
                .collect(Collectors.toList());
    }
}