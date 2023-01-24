package com.springCommerce.commerce.service;

import com.springCommerce.commerce.dto.CreateUserRequest;
import com.springCommerce.commerce.dto.UpdateUserRequest;
import com.springCommerce.commerce.dto.UserDto;
import com.springCommerce.commerce.dto.UserDtoConverter;
import com.springCommerce.commerce.exception.UserNotFoundException;
import com.springCommerce.commerce.model.User;
import com.springCommerce.commerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream().map(userDtoConverter::convert).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id){
        User user = findUserById(id);
        return userDtoConverter.convert(user);
    }

    public UserDto createUser(CreateUserRequest createUserRequest){
        User user = new User(
                createUserRequest.getFirstName(),
                createUserRequest.getMiddleName(),
                createUserRequest.getLastName(),
                createUserRequest.getMail());
        return userDtoConverter.convert(userRepository.save(user));
    }

    private User findUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow( () -> new UserNotFoundException("User could not be found by following id: "+ id));
        return user;
    }

    public UserDto updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = findUserById(id);
        user.setFirstName(updateUserRequest.getFirstName());
        user.setMiddleName(updateUserRequest.getMiddleName());
        user.setLastName(updateUserRequest.getLastName());
        user.setMail(updateUserRequest.getMail());

        return userDtoConverter.convert(userRepository.save(user));
    }
}
