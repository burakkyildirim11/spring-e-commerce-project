package com.springCommerce.commerce.repository;

import com.springCommerce.commerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

  Optional<Users> findByMail(String mail);
}
