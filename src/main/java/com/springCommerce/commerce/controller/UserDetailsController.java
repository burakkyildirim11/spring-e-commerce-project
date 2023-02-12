package com.springCommerce.commerce.controller;

import com.springCommerce.commerce.service.*;
import com.springCommerce.commerce.dto.*;
import com.springCommerce.commerce.model.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user-details")
public class UserDetailsController {

  private final UserDetailsService userDetailsService;

  public UserDetailsController(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @PostMapping
  public ResponseEntity<UserDetailsDto> createUserDetails(@RequestBody CreateUserDetailsRequest request) {
    return ResponseEntity.ok(userDetailsService.createUserDetails(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDetailsDto> updateUserDetails(@PathVariable Long id ,@RequestBody UpdateUserDetailsRequest request) {
    return ResponseEntity.ok(userDetailsService.updateUserDetails(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserDetails(@PathVariable Long id) {
    userDetailsService.deleteUserDetails(id);
    return ResponseEntity.ok().build();
  }

<<<<<<< HEAD
}
=======
}
>>>>>>> e0ccc1d1a10e3cc3ce19b2021b7aaa80e32cefc2
