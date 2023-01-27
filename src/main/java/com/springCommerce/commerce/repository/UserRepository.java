package com.springCommerce.commerce.repository;

import com.springCommerce.commerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByMail(String mail);
}
