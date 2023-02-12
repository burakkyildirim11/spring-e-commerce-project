package com.springCommerce.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springCommerce.commerce.model.UserDetails;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {}