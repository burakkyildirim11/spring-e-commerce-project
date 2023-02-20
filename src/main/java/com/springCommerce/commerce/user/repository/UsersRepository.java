package com.springCommerce.commerce.user.repository;

import com.springCommerce.commerce.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

  Optional<Users> findByMail(String mail);
}
