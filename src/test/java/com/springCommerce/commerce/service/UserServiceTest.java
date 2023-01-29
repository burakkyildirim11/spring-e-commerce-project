package com.springCommerce.commerce.service;

import com.springCommerce.commerce.dto.CreateUserRequest;
import com.springCommerce.commerce.model.User;
import com.springCommerce.commerce.dto.*;
import com.springCommerce.commerce.exception.*;
import com.springCommerce.commerce.TestSupport;
import com.springCommerce.commerce.dto.UserDtoConverter;
import com.springCommerce.commerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest extends TestSupport {

  private UserDtoConverter converter;
  private UserRepository repository;

  private UserService userService;

  @BeforeAll
  public void setUp() {
    converter = mock(UserDtoConverter.class);
    repository = mock(UserRepository.class);

    userService = new UserService(repository, converter);
  }

  @Test
  public void testGetAllUsers_itShouldReturnUserDtoList() {
    List<User> userList = generateUsers();
    List<UserDto> userDtoList = generateUserDtoList(userList);

    when(repository.findAll()).thenReturn(userList);
    when(converter.convert(userList)).thenReturn(userDtoList);

    List<UserDto> result = userService.getAllUsers();

    assertEquals(userDtoList, result);
    verify(repository).findAll();
    verify(converter).convert(userList);
  }

  @Test
  public void testGetUserByMail_whenUserMailExist_itShouldReturnUserDto() {
    String mail = "test@mail.com";
    User user = generateUser(mail);
    UserDto userDto = generateUserDto(mail);

    when(repository.findByMail(mail)).thenReturn(Optional.of(user));
    when(converter.convert(user)).thenReturn(userDto);

    UserDto result = userService.getUserByMail(mail);

    assertEquals(userDto, result);
    verify(repository).findByMail(mail);
    verify(converter).convert(user);
  }

  @Test
  public void testGetUserByMail_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException() {
    String mail = "test@mail.com";

    when(repository.findByMail(mail)).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> userService.getUserByMail(mail));

    verify(repository).findByMail(mail);
    verifyNoInteractions(converter);
  }

  @Test
  public void testCreateUser_itShouldReturnCreatedUserDto() {

    String mail = "test@mail.com";
    CreateUserRequest request = new CreateUserRequest("firstName", "middleName", "lastName", mail);
    User user = new User("firstName", "middleName", "lastName", mail, false);
    User savedUser = new User(1L, "firstName", "middleName", "lastName", mail, false);
    UserDto userDto = new UserDto("firstName", "middleName", "lastName", mail);

    when(repository.save(user)).thenReturn(savedUser);
    when(converter.convert(savedUser)).thenReturn(userDto);

    UserDto result = userService.createUser(request);

    assertEquals(userDto, result);

    verify(repository).save(user);
    verify(converter).convert(savedUser);
  }
}
