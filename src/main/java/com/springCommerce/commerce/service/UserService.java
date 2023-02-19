package com.springCommerce.commerce.service;

import com.springCommerce.commerce.dto.CreateUserRequest;
import com.springCommerce.commerce.dto.UpdateUserRequest;
import com.springCommerce.commerce.dto.UserDto;
import com.springCommerce.commerce.dto.UserDtoConverter;
import com.springCommerce.commerce.exception.*;
import com.springCommerce.commerce.model.BasicUser;
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
    BasicUser basicUser = findUserByMail(mail);
    return userDtoConverter.convert(basicUser);
  }

  public UserDto createUser(final CreateUserRequest createUserRequest) {
    BasicUser basicUser =
            new BasicUser(
                    createUserRequest.getFirstName(),
                    createUserRequest.getMiddleName(),
                    createUserRequest.getLastName(),
                    createUserRequest.getMail(),
                    false);
    return userDtoConverter.convert(userRepository.save(basicUser));
  }

  protected BasicUser findUserById(final Long id) {
    return userRepository
            .findById(id)
            .orElseThrow(
                    () -> new UserNotFoundException("User could not be found by following id: " + id));
  }

  private BasicUser findUserByMail(final String mail) {
    return userRepository
            .findByMail(mail)
            .orElseThrow(
                    () -> new UserNotFoundException("User could not be found by following mail: " + mail));
  }

  public UserDto updateUser(final String mail, final UpdateUserRequest updateUserRequest) {
    BasicUser basicUser = findUserByMail(mail);
    if (!basicUser.getIsActive()) {
      logger.warn("The user wanted update is not active!, user mail: {}", mail);
      throw new UserIsNotActiveException("The user wanted update is not active!");
    }

    basicUser.setFirstName(updateUserRequest.getFirstName());
    basicUser.setMiddleName(updateUserRequest.getMiddleName());
    basicUser.setLastName(updateUserRequest.getLastName());
    basicUser.setMail(basicUser.getMail());
    basicUser.setIsActive(basicUser.getIsActive());

    return userDtoConverter.convert(userRepository.save(basicUser));
  }

  public void deactivateUser(final Long id) {
    changeActivateUser(id, false);
  }

  public void activateUser(final Long id) {
    changeActivateUser(id, true);
  }

  private void changeActivateUser(final Long id, final Boolean isActive) {
    BasicUser basicUser = findUserById(id);
    basicUser.setIsActive(isActive);
    userRepository.save(basicUser);
  }

  public void deleteUser(final Long id) {
    findUserById(id);
    userRepository.deleteById(id);
  }
}