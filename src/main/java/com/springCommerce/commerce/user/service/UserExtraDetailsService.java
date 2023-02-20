package com.springCommerce.commerce.user.service;

import com.springCommerce.commerce.user.dto.CreateUserExtraDetailsRequest;
import com.springCommerce.commerce.user.dto.UpdateUserExtraDetailsRequest;
import com.springCommerce.commerce.user.dto.UserExtraDetailsDto;
import com.springCommerce.commerce.user.dto.UserExtraDetailsDtoConverter;
import com.springCommerce.commerce.user.exception.UserExtraDetailsNotFoundException;
import com.springCommerce.commerce.user.model.UserExtraDetails;
import com.springCommerce.commerce.user.model.Users;
import com.springCommerce.commerce.user.repository.UserExtraDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserExtraDetailsService {

  private final UserExtraDetailsRepository userExtraDetailsRepository;
  private final UserService userService;
  private final UserExtraDetailsDtoConverter converter;

  public UserExtraDetailsService(
      UserExtraDetailsRepository userExtraDetailsRepository,
      UserService userService,
      UserExtraDetailsDtoConverter converter) {
    this.userExtraDetailsRepository = userExtraDetailsRepository;
    this.userService = userService;
    this.converter = converter;
  }

  public UserExtraDetailsDto createUserDetails(final CreateUserExtraDetailsRequest request) {

    Users user = userService.findUserById(request.getUserId());

    UserExtraDetails userExtraDetails =
        new UserExtraDetails(
            request.getPhoneNumber(),
            request.getAddress(),
            request.getCity(),
            request.getCountry(),
            request.getPostCode(),
            user);

    return converter.convert(userExtraDetailsRepository.save(userExtraDetails));
  }

  public UserExtraDetailsDto updateUserDetails(
      final Long userDetailsId, final UpdateUserExtraDetailsRequest request) {

    UserExtraDetails user = findUserDetailsById(userDetailsId);

    user.setAddress(request.getAddress());
    user.setCity(request.getCity());
    user.setPhoneNumber(request.getPhoneNumber());
    user.setCountry(request.getCountry());
    user.setPostCode(request.getPostCode());

    return converter.convert(userExtraDetailsRepository.save(user));
  }

  public void deleteUserDetails(final Long userDetailsId) {
    UserExtraDetails user = findUserDetailsById(userDetailsId);
    userExtraDetailsRepository.delete(user);
  }

  private UserExtraDetails findUserDetailsById(final Long userDetailsId) {
    return userExtraDetailsRepository
        .findById(userDetailsId)
        .orElseThrow(
            () ->
                new UserExtraDetailsNotFoundException(
                    "User details could not be found by following user details id: "
                        + userDetailsId));
  }
}
