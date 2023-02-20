package com.springCommerce.commerce.user.controller;

import com.springCommerce.commerce.user.dto.CreateUserExtraDetailsRequest;
import com.springCommerce.commerce.user.dto.UpdateUserExtraDetailsRequest;
import com.springCommerce.commerce.user.dto.UserExtraDetailsDto;
import com.springCommerce.commerce.user.service.UserExtraDetailsService;
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
