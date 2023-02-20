//package com.springCommerce.commerce.service;
//
//import CreateUserRequest;
//import Users;
//import com.springCommerce.commerce.dto.*;
//import com.springCommerce.commerce.exception.*;
//import com.springCommerce.commerce.TestSupport;
//import UserDtoConverter;
//import UsersRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class UserServiceTest extends TestSupport {
//
//  private UserDtoConverter converter;
//  private UsersRepository repository;
//
//  private UserService userService;
//
//  @BeforeEach
//  public void setUp() {
//    converter = mock(UserDtoConverter.class);
//    repository = mock(UsersRepository.class);
//
//    userService = new UserService(repository, converter);
//  }
//
//  @Test
//  void testGetAllUsers_itShouldReturnUserDtoList() {
//    List<Users> userList = generateUsers();
//    List<UserDto> userDtoList = generateUserDtoList(userList);
//
//    when(repository.findAll()).thenReturn(userList);
//    when(converter.convert(userList)).thenReturn(userDtoList);
//
//    List<UserDto> result = userService.getAllUsers();
//
//    assertEquals(userDtoList, result);
//    verify(repository).findAll();
//    verify(converter).convert(userList);
//  }
//
//  @Test
//  void testGetUserByMail_whenUserMailExist_itShouldReturnUserDto() {
//    String mail = "test@mail.com";
//    Users user = generateUser(mail);
//    UserDto userDto = generateUserDto(mail);
//
//    when(repository.findByMail(mail)).thenReturn(Optional.of(user));
//    when(converter.convert(user)).thenReturn(userDto);
//
//    UserDto result = userService.getUserByMail(mail);
//
//    assertEquals(userDto, result);
//    verify(repository).findByMail(mail);
//    verify(converter).convert(user);
//  }
//
//  @Test
//  void testGetUserByMail_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException() {
//    String mail = "test@mail.com";
//
//    when(repository.findByMail(mail)).thenReturn(Optional.empty());
//
//    assertThrows(UserNotFoundException.class, () -> userService.getUserByMail(mail));
//
//    verify(repository).findByMail(mail);
//    verifyNoInteractions(converter);
//  }
//
//  @Test
//  void testCreateUser_itShouldReturnCreatedUserDto() {
//
//    String mail = "test@mail.com";
//    CreateUserRequest request = new CreateUserRequest("firstName", "middleName", "lastName", mail);
//    Users user = new Users("firstName", "middleName", "lastName", mail, false);
//    Users savedUser = new Users(1L, "firstName", "middleName", "lastName", mail, false);
//    UserDto userDto = new UserDto("firstName", "middleName", "lastName", mail);
//
//    when(repository.save(user)).thenReturn(savedUser);
//    when(converter.convert(savedUser)).thenReturn(userDto);
//
//    UserDto result = userService.createUser(request);
//
//    assertEquals(userDto, result);
//
//    verify(repository).save(user);
//    verify(converter).convert(savedUser);
//  }
//
//  @Test
//  void testUpdateUser_whenUserMailExistAndUserIsActive_itShouldReturnUpdatedUserDto() {
//
//    String mail = "test@mail.com";
//    UpdateUserRequest request = new UpdateUserRequest("firstName2", "middleName2", "lastName2");
//    Users user = new Users(1L, "firstName", "middleName", "lastName", mail, true);
//    Users savedUser = new Users(1L, "firstName2", "middleName2", "lastName2", mail, true);
//    Users updateUser = new Users(1L, "firstName2", "middleName2", "lastName2", mail, true);
//    UserDto userDto = new UserDto("firstName2", "middleName2", "lastName2", mail,);
//
//    when(repository.findByMail(mail)).thenReturn(Optional.of(user));
//    when(repository.save(updateUser)).thenReturn(savedUser);
//    when(converter.convert(savedUser)).thenReturn(userDto);
//
//    UserDto result = userService.updateUser(mail, request);
//
//    assertEquals(userDto, result);
//
//    verify(repository).findByMail(mail);
//    verify(repository).save(updateUser);
//    verify(converter).convert(savedUser);
//  }
//
//  @Test
//  void testUpdateUser_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException() {
//
//    String mail = "test@mail.com";
//    UpdateUserRequest request = new UpdateUserRequest("firstName2", "middleName2", "lastName2");
//
//    when(repository.findByMail(mail)).thenReturn(Optional.empty());
//
//    assertThrows(UserNotFoundException.class, () -> userService.updateUser(mail, request));
//
//    verify(repository).findByMail(mail);
//    verifyNoMoreInteractions(repository);
//    verifyNoInteractions(converter);
//  }
//
//  @Test
//  void testUpdateUser_whenUserMailExistButUserIsNotActive_itShouldThrowUserNotActiveException() {
//
//    String mail = "test@mail.com";
//    UpdateUserRequest request = new UpdateUserRequest("firstName2", "middleName2", "lastName2");
//    Users user = new Users(1L, "firstName", "middleName", "lastName", mail, false);
//
//    when(repository.findByMail(mail)).thenReturn(Optional.of(user));
//
//    assertThrows(UserIsNotActiveException.class, () -> userService.updateUser(mail, request));
//
//    verify(repository).findByMail(mail);
//    verifyNoMoreInteractions(repository);
//    verifyNoInteractions(converter);
//  }
//
//  @Test
//  void testDeactivateUser_whenUserIdExist_itShouldUserByActiveFalse() {
//
//    Users user = new Users(userId, "firstName", "middleName", "lastName", "test@mail.com", true);
//    Users savedUser =
//        new Users(userId, "firstName", "middleName", "lastName", "test@mail.com", false);
//
//    when(repository.findById(userId)).thenReturn(Optional.of(user));
//
//    userService.deactivateUser(userId);
//
//    verify(repository).findById(userId);
//    verify(repository).save(savedUser);
//  }
//
//  @Test
//  void testDeactivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {
//
//    when(repository.findById(userId)).thenReturn(Optional.empty());
//
//    assertThrows(UserNotFoundException.class, () -> userService.deactivateUser(userId));
//
//    verify(repository).findById(userId);
//    verifyNoMoreInteractions(repository);
//  }
//
//  @Test
//  void testActivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {
//
//    when(repository.findById(userId)).thenReturn(Optional.empty());
//
//    assertThrows(UserNotFoundException.class, () -> userService.activateUser(userId));
//
//    verify(repository).findById(userId);
//    verifyNoMoreInteractions(repository);
//  }
//
//  @Test
//  void testActivateUser_whenUserIdExist_itShouldUserActiveTrue() {
//
//    Users user = new Users(userId, "firstName", "middleName", "lastName", "test@mail.com", false);
//    Users savedUser =
//        new Users(userId, "firstName", "middleName", "lastName", "test@mail.com", true);
//
//    when(repository.findById(userId)).thenReturn(Optional.of(user));
//
//    userService.activateUser(userId);
//
//    verify(repository).findById(userId);
//    verify(repository).save(savedUser);
//  }
//
//  @Test
//  void testDeleteUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {
//
//    when(repository.findById(userId)).thenReturn(Optional.empty());
//
//    assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
//
//    verify(repository).findById(userId);
//    verifyNoMoreInteractions(repository);
//  }
//
//  @Test
//  void testDeleteUser_whenUserIdExist_itShouldDeleteUser() {
//
//    Users user = new Users(userId, "firstName", "middleName", "lastName", "test@mail.com", true);
//
//    when(repository.findById(userId)).thenReturn(Optional.of(user));
//
//    userService.deleteUser(userId);
//
//    verify(repository).findById(userId);
//    verify(repository).deleteById(userId);
//  }
//}
