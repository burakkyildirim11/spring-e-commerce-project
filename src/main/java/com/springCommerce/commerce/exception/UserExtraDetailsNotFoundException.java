package com.springCommerce.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserExtraDetailsNotFoundException extends RuntimeException {

    public UserExtraDetailsNotFoundException(String message) {
        super(message);
    }
}