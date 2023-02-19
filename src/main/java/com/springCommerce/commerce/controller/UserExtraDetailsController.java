package com.springCommerce.commerce.controller;

import com.springCommerce.commerce.service.*;
import com.springCommerce.commerce.dto.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user-details")
public class UserExtraDetailsController {

  private final UserExtraDetailsService userExtraDetailsService;

  public UserExtraDetailsController(UserExtraDetailsService userExtraDetailsService) {
    this.userExtraDetailsService = userExtraDetailsService;
  }

  @PostMapping
  public ResponseEntity<UserExtraDetailsDto> createUserDetails(
      @RequestBody CreateUserExtraDetailsRequest request) {
    return ResponseEntity.ok(userExtraDetailsService.createUserDetails(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserExtraDetailsDto> updateUserDetails(
      @PathVariable Long id, @RequestBody UpdateUserExtraDetailsRequest request) {
    return ResponseEntity.ok(userExtraDetailsService.updateUserDetails(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserDetails(@PathVariable Long id) {
    userExtraDetailsService.deleteUserDetails(id);
    return ResponseEntity.ok().build();
  }
  }
