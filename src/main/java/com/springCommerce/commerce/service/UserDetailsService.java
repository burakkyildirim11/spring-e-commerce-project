package com.springCommerce.commerce.service;

import com.springCommerce.commerce.exception.UserDetailsNotFoundException;
import org.springframework.stereotype.Service;
import com.springCommerce.commerce.dto.*;
import com.springCommerce.commerce.model.*;
import com.springCommerce.commerce.repository.*;

import java.util.Optional;

@Service
public class UserDetailsService {

  private final UserDetailsRepository userDetailsRepository;
  private final UserService userService;
  private final UserDetailsDtoConverter converter;

  public UserDetailsService(
      UserDetailsRepository userDetailsRepository,
      UserService userService,
      UserDetailsDtoConverter converter) {
    this.userDetailsRepository = userDetailsRepository;
    this.userService = userService;
    this.converter = converter;
  }

  public UserDetailsDto createUserDetails(final CreateUserDetailsRequest request) {

    Users user = userService.findUserById(request.getUserId());

    UserDetails userDetails =
        new UserDetails(
            request.getPhoneNumber(),
            request.getAddress(),
            request.getCity(),
            request.getCountry(),
            request.getPostCode(),
            user);

    return converter.convert(userDetailsRepository.save(userDetails));
  }

  public UserDetailsDto updateUserDetails(
      final Long userDetailsId, final UpdateUserDetailsRequest request) {

    UserDetails user = findUserDetailsById(userDetailsId);

    user.setAddress(request.getAddress());
    user.setCity(request.getCity());
    user.setPhoneNumber(request.getPhoneNumber());
    user.setCountry(request.getCountry());
    user.setPostCode(request.getPostCode());

    return converter.convert(userDetailsRepository.save(user));
  }

  public void deleteUserDetails(final Long userDetailsId) {
    UserDetails user = findUserDetailsById(userDetailsId);
    userDetailsRepository.delete(user);
  }

  private UserDetails findUserDetailsById(final Long userDetailsId) {
    return userDetailsRepository
        .findById(userDetailsId)
        .orElseThrow(
            () ->
                new UserDetailsNotFoundException(
                    "User details could not be found by following user details id: "
                        + userDetailsId));
  }
}
