package com.springCommerce.commerce.dto;

import com.springCommerce.commerce.model.BasicUser;
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

    public UserDto convert(BasicUser basicUser) {
        return new UserDto(
                basicUser.getFirstName(),
                basicUser.getMiddleName(),
                basicUser.getLastName(),
                basicUser.getMail(),
                converter.convert(new ArrayList<>(basicUser.getUserDetailsSet())));
    }

    public List<UserDto> convert(List<BasicUser> basicBasicUserList) {
        return basicBasicUserList.stream()
                .map(
                        user ->
                                new UserDto(
                                        user.getFirstName(),
                                        user.getMiddleName(),
                                        user.getLastName(),
                                        user.getMail(),
                                        converter.convert(new ArrayList<>(user.getUserDetailsSet()))))
                .collect(Collectors.toList());
    }
}