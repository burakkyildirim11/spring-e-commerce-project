package com.springCommerce.commerce.controller;

import com.springCommerce.commerce.dto.CreateUserRequest;
import com.springCommerce.commerce.dto.UpdateUserRequest;
import com.springCommerce.commerce.dto.UserDto;
import com.springCommerce.commerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping("/{mail}")
  public ResponseEntity<UserDto> getUserByMail(
<<<<<<< HEAD
          @PathVariable(value = "mail") String mail) {
=======
      @PathVariable(value = "mail") String mail) {
>>>>>>> e0ccc1d1a10e3cc3ce19b2021b7aaa80e32cefc2
    return ResponseEntity.ok(userService.getUserByMail(mail));
  }

  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest userRequest) {
    return ResponseEntity.ok(userService.createUser(userRequest));
  }

  @PutMapping("/{mail}")
  public ResponseEntity<UserDto> updateUser(
      @PathVariable("mail") String mail, @RequestBody UpdateUserRequest updateUserRequest) {
    return ResponseEntity.ok(userService.updateUser(mail, updateUserRequest));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> deactivateUser(@PathVariable("id") Long id) {
    userService.deactivateUser(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}/active")
  public ResponseEntity<Void> activeUser(@PathVariable("id") Long id) {
    userService.activateUser(id);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok().build();
  }
}
