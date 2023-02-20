package com.springCommerce.commerce.user.service;

import com.springCommerce.commerce.config.JwtService;
import com.springCommerce.commerce.user.dto.AuthenticationResponse;
import com.springCommerce.commerce.user.dto.CreateLoginRequest;
import com.springCommerce.commerce.user.dto.CreateUserRequest;
import com.springCommerce.commerce.user.model.Users;
import com.springCommerce.commerce.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UsersRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(CreateUserRequest createUserRequest) {

    Users user =
        new Users(
            createUserRequest.getFirstName(),
            createUserRequest.getMiddleName(),
            createUserRequest.getLastName(),
            createUserRequest.getMail(),
            false,
            passwordEncoder.encode(createUserRequest.getPassword()));

    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);

    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public AuthenticationResponse authenticate(CreateLoginRequest createLoginRequest) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            createLoginRequest.getMail(), createLoginRequest.getPassword()));

    var user = userRepository.findByMail(createLoginRequest.getMail()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);

    return AuthenticationResponse.builder().token(jwtToken).build();
  }
}
