package com.springCommerce.commerce.user.service;

import com.springCommerce.commerce.user.dto.CreateUserRequest;
import com.springCommerce.commerce.user.dto.UpdateUserRequest;
import com.springCommerce.commerce.user.dto.UserDto;
import com.springCommerce.commerce.user.dto.UserDtoConverter;
import com.springCommerce.commerce.user.exception.UserIsNotActiveException;
import com.springCommerce.commerce.user.exception.UserNotFoundException;
import com.springCommerce.commerce.user.model.Users;
import com.springCommerce.commerce.user.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  private final UsersRepository usersRepository;
  private final UserDtoConverter userDtoConverter;

  public UserService(UsersRepository usersRepository, UserDtoConverter userDtoConverter) {
    this.usersRepository = usersRepository;
    this.userDtoConverter = userDtoConverter;
  }

  public List<UserDto> getAllUsers() {
    return userDtoConverter.convert(usersRepository.findAll());
  }

  public UserDto getUserByMail(String mail) {
    Users user = findUserByMail(mail);
    return userDtoConverter.convert(user);
  }

  public UserDto createUser(final CreateUserRequest createUserRequest) {
    Users user =
        new Users(
            createUserRequest.getFirstName(),
            createUserRequest.getMiddleName(),
            createUserRequest.getLastName(),
            createUserRequest.getMail(),
            false,
            createUserRequest.getPassword());
    return userDtoConverter.convert(usersRepository.save(user));
  }

  protected Users findUserById(final Long id) {
    return usersRepository
        .findById(id)
        .orElseThrow(
            () -> new UserNotFoundException("User could not be found by following id: " + id));
  }

  private Users findUserByMail(final String mail) {
    return usersRepository
        .findByMail(mail)
        .orElseThrow(
            () -> new UserNotFoundException("User could not be found by following mail: " + mail));
  }

  public UserDto updateUser(final String mail, final UpdateUserRequest updateUserRequest) {
    Users user = findUserByMail(mail);
    if (!user.getIsActive()) {
      logger.warn("The user wanted update is not active!, user mail: {}", mail);
      throw new UserIsNotActiveException("The user wanted update is not active!");
    }

    user.setFirstName(updateUserRequest.getFirstName());
    user.setMiddleName(updateUserRequest.getMiddleName());
    user.setLastName(updateUserRequest.getLastName());
    user.setMail(user.getMail());
    user.setIsActive(user.getIsActive());

    return userDtoConverter.convert(usersRepository.save(user));
  }

  public void deactivateUser(final Long id) {
    changeActivateUser(id, false);
  }

  public void activateUser(final Long id) {
    changeActivateUser(id, true);
  }

  private void changeActivateUser(final Long id, final Boolean isActive) {
    Users user = findUserById(id);
    user.setIsActive(isActive);
    usersRepository.save(user);
  }

  public void deleteUser(final Long id) {
    findUserById(id);
    usersRepository.deleteById(id);
  }
}
