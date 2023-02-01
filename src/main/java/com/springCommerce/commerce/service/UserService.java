package com.springCommerce.commerce.service;

import com.springCommerce.commerce.dto.CreateUserRequest;
import com.springCommerce.commerce.dto.UpdateUserRequest;
import com.springCommerce.commerce.dto.UserDto;
import com.springCommerce.commerce.dto.UserDtoConverter;
import com.springCommerce.commerce.exception.*;
import com.springCommerce.commerce.model.User;
import com.springCommerce.commerce.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  private final UserRepository userRepository;
  private final UserDtoConverter userDtoConverter;

  public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
    this.userRepository = userRepository;
    this.userDtoConverter = userDtoConverter;
  }

  public List<UserDto> getAllUsers() {
    return userDtoConverter.convert(userRepository.findAll());
  }

  public UserDto getUserByMail(String mail) {
    User user = findUserByMail(mail);
    return userDtoConverter.convert(user);
  }

  public UserDto createUser(CreateUserRequest createUserRequest) {
    User user =
        new User(
            createUserRequest.getFirstName(),
            createUserRequest.getMiddleName(),
            createUserRequest.getLastName(),
            createUserRequest.getMail(),
            false);
    return userDtoConverter.convert(userRepository.save(user));
  }

  private User findUserById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(
            () -> new UserNotFoundException("User could not be found by following id: " + id));
  }

  private User findUserByMail(String mail) {
    return userRepository
        .findByMail(mail)
        .orElseThrow(
            () -> new UserNotFoundException("User could not be found by following mail: " + mail));
  }

  public UserDto updateUser(String mail, UpdateUserRequest updateUserRequest) {
    User user = findUserByMail(mail);
    if (!user.getIsActive()) {
      logger.warn("The user wanted update is not active!, user mail: {}", mail);
      throw new UserIsNotActiveException("The user wanted update is not active!");
    }

    user.setFirstName(updateUserRequest.getFirstName());
    user.setMiddleName(updateUserRequest.getMiddleName());
    user.setLastName(updateUserRequest.getLastName());
    user.setMail(user.getMail());
    user.setIsActive(user.getIsActive());

    return userDtoConverter.convert(userRepository.save(user));
  }

  public void deactivateUser(Long id) {
    changeActivateUser(id, false);
  }

  public void activateUser(Long id) {
    changeActivateUser(id, true);
  }

  private void changeActivateUser(Long id, Boolean isActive) {
    User user = findUserById(id);
    user.setIsActive(isActive);
    userRepository.save(user);
  }

  public void deleteUser(Long id) {
    findUserById(id);
    userRepository.deleteById(id);
  }
}
